package com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.Model.Zip;
import com.Model.ZipRepository;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller    // This means that this class is a Controller
//@RequestMapping(path="/zip") // This means URL's start with /demo (after Application path)
public class ZipController {
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private ZipRepository zipRepository;

    @GetMapping(path="/add") // Map ONLY GET Requests
    public @ResponseBody String addNewUser (@RequestParam String name) {

        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Zip n = new Zip();
        n.setName(name);
        n.setCurrentCreationDate();
        zipRepository.save(n);
        return "Saved";
    }

    @GetMapping(value="/zips")
    public String getAllUsers(Model model) {
        model.addAttribute("zipList", zipRepository.findAll());
        model.addAttribute("newzip", new Zip());
        return "all";
    }

//    @GetMapping(path="/all")
//    public @ResponseBody Iterable<Zip> getAllUsers() {
//        // This returns a JSON or XML with the users
//        return zipRepository.findAll();
//    }

    @GetMapping(path="/hello")//RequestMapping
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }

    @RequestMapping(method=DELETE, value="zips/{id}" )
    public String delete(@PathVariable Integer id) {
        zipRepository.delete(id);
        return "redirect:/zips";
    }

    //@GetMapping(path="/hello")//RequestMapping
    @RequestMapping(method=POST, value="/zips")
    public String add(@ModelAttribute Zip z) {
        //Zip n = new Zip();
        //n.setName(z.getName());
        z.setCurrentCreationDate();
        zipRepository.save(z);
        return "redirect:/zips";
    }

    @RequestMapping(method=PUT, value="zips/{id}" )
    public String update(@PathVariable Integer id) {
        //zipRepository.delete(id);
        return "redirect:/zips";
    }
}
