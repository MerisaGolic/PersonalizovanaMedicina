package ba.etf.personalizovanamedicina;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ba.etf.personalizovanamedicina.Models.*;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ControllerZaSve {
	
	private static final Logger logger = LoggerFactory.getLogger(ControllerZaSve.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		return "home";
	}
	//za pacijente
	@RequestMapping(value = "/izvrsiDijagnozu", method = RequestMethod.POST)
	public String dijagnoza(Locale locale, Model model) {
		
		model.addAttribute("simptomiForm", new IzabraniSimptomi());
		model.addAttribute("dodavanjeDijagnozeForm", new Pacijent());
		
		return "dijagnoza";
	}
	@RequestMapping(value = "/trazenjeDijagnozePacijent", method = RequestMethod.POST)
	public String simptomi1(Model model, @ModelAttribute("simptomiForm") IzabraniSimptomi is) 
	{
		model.addAttribute("simptomiForm", new IzabraniSimptomi());
		model.addAttribute("dodavanjeDijagnozeForm", new Pacijent());
		
		String delims = "[,]";
		String[] tokens = is.getIzabraniSimptomi().split(delims);
		ArrayList<String> simptomi = new ArrayList<String>(Arrays.asList(tokens));
		
		SimptomiDijagnozeDAO sdDAO = new SimptomiDijagnozeDAO();
		
		sdDAO.dajSveDijagnoze(simptomi, is.getPostotak());
		
		return "dijagnoza";
	}
	@RequestMapping(value = "/novaDijagnoza", method = RequestMethod.POST)
	public String metoda1(Model model, @ModelAttribute("dodavanjeDijagnozeForm") Pacijent p) 
	{
		model.addAttribute("simptomiForm", new IzabraniSimptomi());
		model.addAttribute("dodavanjeDijagnozeForm", new Pacijent());
		
		SimptomiDijagnozeDAO sdDAO = new SimptomiDijagnozeDAO();
		sdDAO.resetujDijagnoze();
		
		return "dijagnoza";
	}
	//za doktore
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Locale locale, Model model) {
		logger.info("Welcome to login! The client locale is {}.", locale);
		model.addAttribute("loginForm", new Korisnik());
		
		return "login";
	}
	
	@RequestMapping(value = "/provjeraLogina", method = RequestMethod.POST)
	public String login(Model model, @ModelAttribute("loginForm") Korisnik k, HttpSession sesija) 
	{
		model.addAttribute("loginForm", new Korisnik());
		model.addAttribute("simptomiForm", new IzabraniSimptomi());
		model.addAttribute("dodavanjeDijagnozeForm", new Pacijent());
		model.addAttribute("novaDijagnozaForm", new Dijagnoza());
		
		LoginDAO loginDAO = new LoginDAO();
		
		if(loginDAO.provjeriPodatke(k.getUsername(), k.getPassword()))	{
			sesija.setAttribute("korisnik", k);
			SimptomiDijagnozeDAO sdDAO = new SimptomiDijagnozeDAO();
			sdDAO.resetujDijagnoze();
			
			return "drDijagnoza";
		}
		model.addAttribute("LoginFailed", "Pogresni login podaci!"); 
		return "login";
	}
	@RequestMapping(value = "/dijagnosticiranje", method = RequestMethod.POST)
	public String login2(Model model, HttpSession sesija) 
	{
		model.addAttribute("loginForm", new Korisnik());
		model.addAttribute("simptomiForm", new IzabraniSimptomi());
		model.addAttribute("dodavanjeDijagnozeForm", new Pacijent());
		model.addAttribute("novaDijagnozaForm", new Dijagnoza());
		

		return "drDijagnoza";
	}
	@RequestMapping("/odjava")
	public String odjava(HttpSession sesija)
	{
		sesija.removeAttribute("loggedInUser");
		sesija.removeAttribute("pacijentID");
		sesija.removeAttribute("nazivDijagnoze");
		sesija.removeAttribute("listaSimptoma");
		sesija.invalidate();
		return "home";
	}

	@RequestMapping(value = "/trazenjeDijagnoze", method = RequestMethod.POST)
	public String simptomi(Model model, @ModelAttribute("simptomiForm") IzabraniSimptomi is, HttpSession sesija) 
	{
		model.addAttribute("simptomiForm", new IzabraniSimptomi());
		model.addAttribute("dodavanjeDijagnozeForm", new Pacijent());
		model.addAttribute("novaDijagnozaForm", new Dijagnoza());
		
		SimptomiDijagnozeDAO sdDAO = new SimptomiDijagnozeDAO();
		sdDAO.resetujDijagnoze();
		
		if(is.getIzabraniSimptomi() == "")
			model.addAttribute("prazniSimptomi", "Nije izabran nijedan simptom!");
		else
			{
			String delims = "[,]";
			String[] tokens = is.getIzabraniSimptomi().split(delims);
			ArrayList<String> simptomi = new ArrayList<String>(Arrays.asList(tokens));
			
			sesija.setAttribute("listaSimptoma", simptomi);
			model.addAttribute("da", "da");
			sdDAO.dajSveDijagnoze(simptomi, is.getPostotak());
			}
		
		
		
		return "drDijagnoza";
	}
	@RequestMapping(value = "/dodavanjeDijagnoze", method = RequestMethod.POST)
	public String metoda(Model model, @ModelAttribute("dodavanjeDijagnozeForm") Pacijent p, HttpSession sesija) 
	{
		model.addAttribute("simptomiForm", new IzabraniSimptomi());
		model.addAttribute("dodavanjeDijagnozeForm", new Pacijent());
		model.addAttribute("novaDijagnozaForm", new Dijagnoza());
		
		PacijentiDAO pDAO = new PacijentiDAO();
		Korisnik k = (Korisnik)sesija.getAttribute("korisnik");
		if(p.getImeiprezime() == "" || p.getDatumRodjenja() == "" || p.getDijagnoza() == "" || p.getSpol() =="")
			model.addAttribute("podaci", "Morate unijeti sve podatke o pacijentu da biste mu pridruzili dijagnozu!");
		
		else
			{
			pDAO.dodajDijagnozu(p, k);
			SimptomiDijagnozeDAO sdDAO = new SimptomiDijagnozeDAO();
			sdDAO.resetujDijagnoze();
			sesija.removeAttribute("listaSimptoma");
			}
		
		
		
		return "drDijagnoza";
	}
	
	@RequestMapping("/spisakPacijenata")
	public String spisak(Model model, HttpSession sesija)
	{
		PacijentiDAO pDAO = new PacijentiDAO();
		List<Pacijent> listaPacijenata = new ArrayList<Pacijent>();
		
		listaPacijenata = pDAO.dajSvePacijente((Korisnik)sesija.getAttribute("korisnik"));
		model.addAttribute("spisakPacijenataForm", listaPacijenata);
		model.addAttribute("detaljanPrikazForm", new Pacijent());
		model.addAttribute("novaDijagnozaForm", new Dijagnoza());
		
		sesija.removeAttribute("pacijentID");
		sesija.removeAttribute("nazivDijagnoze");
		
		return "spisakPacijenata"; //return model;
	}
	
	@RequestMapping(value = "/detaljanPrikaz", method = RequestMethod.POST)
	public String metoda2(Model model, @ModelAttribute("detaljanPrikazForm") Pacijent p, HttpSession sesija) 
	{
		model.addAttribute("simptomiForm", new IzabraniSimptomi());
		model.addAttribute("dodavanjeDijagnozeForm", new Pacijent());
		model.addAttribute("detaljanPrikazForm", new Pacijent());
		model.addAttribute("racunanjeDozeForm", new Dijagnoza());
		model.addAttribute("novaDijagnozaForm", new Dijagnoza());
		
		PacijentiDAO pDAO = new PacijentiDAO();

		List<Dijagnoza> listaDijagnoza = new ArrayList<Dijagnoza>();
		listaDijagnoza = pDAO.dajSveDijagnoze(p);
		
		model.addAttribute("detaljanPrikazForm", listaDijagnoza);
		sesija.setAttribute("pacijentID", pDAO.dajIdPacijenta(p));
		
		sesija.removeAttribute("nazivDijagnoze");
		
		return "detaljanPrikazPacijenta";
	}
	
	@RequestMapping(value = "/racunanjeDoze", method = RequestMethod.POST)
	public String metoda3(Model model, @ModelAttribute("racunanjeDozeForm") Dijagnoza d, HttpSession sesija) 
	{
		model.addAttribute("racunanjeDozeForm", new Dijagnoza());
		model.addAttribute("nazivDijagnoze", d.getNaziv());
		sesija.setAttribute("nazivDijagnoze", d.getNaziv());
		model.addAttribute("prikazDozeForm", new Parametri());
		model.addAttribute("novaDijagnozaForm", new Dijagnoza());
		
		Integer id = (Integer)sesija.getAttribute("pacijentID");
		DijagnozePacijentiDAO dpDAO = new DijagnozePacijentiDAO();
		List<String> rezultat = dpDAO.dajParametre(id, d.getNaziv());
		model.addAttribute("secer", rezultat.get(0));
		model.addAttribute("eritrociti", rezultat.get(1));
		model.addAttribute("leukociti", rezultat.get(2));
		model.addAttribute("trombociti", rezultat.get(3));
		model.addAttribute("doza", rezultat.get(4));
		model.addAttribute("nazivLijeka", rezultat.get(5));
		model.addAttribute("nazivDijagnoze", d.getNaziv());
		return "racunanjeDoze";
	}
	
	@RequestMapping(value = "/prikazDoze", method = RequestMethod.POST)
	public String metoda4(Model model, @ModelAttribute("prikazDozeForm") Parametri p, HttpSession sesija) 
	{
		model.addAttribute("prikazDozeForm", new Parametri());
		model.addAttribute("novaDijagnozaForm", new Dijagnoza());
		
		DijagnozePacijentiDAO dpDAO = new DijagnozePacijentiDAO();
		Integer id = (Integer)sesija.getAttribute("pacijentID");
		String naziv = (String)sesija.getAttribute("nazivDijagnoze");
		List<String> rezultat = dpDAO.dodajParametre(id, naziv, p);
		
		
		model.addAttribute("secer", rezultat.get(0));
		model.addAttribute("eritrociti", rezultat.get(1));
		model.addAttribute("leukociti", rezultat.get(2));
		model.addAttribute("trombociti", rezultat.get(3));
		model.addAttribute("doza", rezultat.get(4));
		model.addAttribute("nazivLijeka", rezultat.get(5));
		model.addAttribute("nazivDijagnoze", rezultat.get(6));

		return "racunanjeDoze";
	}
	@RequestMapping(value = "/novaDijagnoza")
	public String metoda5(Model model, HttpSession sesija) {
		
		model.addAttribute("simptomiForm", new IzabraniSimptomi());
		model.addAttribute("dodavanjeDijagnozeForm", new Pacijent());
		model.addAttribute("novaDijagnozaForm", new Dijagnoza());
		Object listaSimpt = sesija.getAttribute("listaSimptoma");
		
		if(listaSimpt == null)
		{
			model.addAttribute("novaDijagnoza", "Nije izabran nijedan simptom! Ne možete dodati novu dijagnozu!");
			return "drDijagnoza";
		}
		
	
		return "novaDijagnoza";
	}
	
	@RequestMapping(value = "/dodajNovuDijagnozu", method = RequestMethod.POST)
	public String metoda5(Model model, @ModelAttribute("novaDijagnozaForm") Dijagnoza d, HttpSession sesija) 
	{
		model.addAttribute("novaDijagnozaForm", new Dijagnoza());
		model.addAttribute("simptomiForm", new IzabraniSimptomi());
		model.addAttribute("dodavanjeDijagnozeForm", new Pacijent());
		SimptomiDijagnozeDAO sdDAO = new SimptomiDijagnozeDAO();
		sdDAO.resetujDijagnoze();
		ArrayList<String> listaSimpt = (ArrayList<String>)sesija.getAttribute("listaSimptoma");
		
		if(d.getDoza() == "" || d.getNaziv() == "" || d.getNazivLijeka() == "" || d.getOpis() == "")
		{
			model.addAttribute("nova", "Morate popuniti sva polja da biste dodali novu dijagnozu!");
			return "novaDijagnoza";
		}		
		else 
			sdDAO.dodajNovuDijagnozu(listaSimpt, d);
		
		return "drDijagnoza";
	}
	
}
