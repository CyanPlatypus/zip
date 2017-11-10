package com.Controller;

import com.Model.Doc;
import com.Model.ZipDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.Model.Zip;
import com.Model.ZipRepository;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
//@RequestMapping(path="/zip") // This means URL's start with /demo (after Application path)
public class ZipController {
    @Autowired
    private ZipDocService zipDocService;
    //private ZipRepository zipRepository;

//    @GetMapping(path="/add") // Map ONLY GET Requests
//    public @ResponseBody String addNewUser (@RequestParam String name) {
//
//        // @ResponseBody means the returned String is the response, not a view name
//        // @RequestParam means it is a parameter from the GET or POST request
//
//        Zip n = new Zip();
//        n.setName(name);
//        n.setCurrentCreationDate();
//        zipDocService.addZip(n);
//        return "Saved";
//    }

    @GetMapping(value="/zips")
    public String showZips(Model model) {
        model.addAttribute("zipList", zipDocService.getAllZips());
        if(!model.containsAttribute("viewD"))
        {
            model.addAttribute("viewD", "none");
        }
        if(!model.containsAttribute("viewC"))
        {
            model.addAttribute("viewC", "none");
        }
        ////model.addAttribute("newzip", new Zip());
        return "fragments/all";
    }

//    @GetMapping(path="/hello")//RequestMapping
//    public String greeting(@RequestParam(value="name", required=false, defaultValue="World")
//                                       String name, Model model) {
//        model.addAttribute("name", name);
//        return "hello";
//    }

    @RequestMapping(method=DELETE, value="/zips/{id}" )
    public String delete(@PathVariable long id) {
        zipDocService.deleteZip(id);
        return "redirect:/zips";
    }

    @RequestMapping(method=DELETE, value="/zips/{idZ}/{idD}" )
    public String deleteDoc(@PathVariable long idZ, @PathVariable long idD) {

        zipDocService.deleteDoc(idZ,idD);
        return "redirect:/zips/{idZ}";
    }


    @RequestMapping(method=POST, value="/zips")
    public String addZip(@ModelAttribute Zip z) {
        z.setCurrentCreationDate();
        zipDocService.addZip(z);
        return "redirect:/zips";
    }

    @RequestMapping(method=PUT, value="/zips" )
    public String update(@ModelAttribute Zip zip) {//RequestBody

        zipDocService.changeZipName(zip.getId(), zip.getName());
        return "redirect:/zips";
    }

    @RequestMapping(method=PUT, value="/zips/{idZ}/{idD}" )
    public String updateDoc(@PathVariable("idZ") long idZ, @PathVariable("idD") long idD,
                            @ModelAttribute Doc d,  Model model) {//RequestBody

        zipDocService.changeZipDocContent(idD, d.getContent());

        return showDoc(idZ,idD,model);
    }

    @RequestMapping(method=GET, value="/zips/{idZ}" )
    public String showDocs(@PathVariable long idZ, Model model) {

        model.addAttribute("docList", zipDocService.getZipDocs(idZ)/*.sort((p1, p2) -> {return p1.getId()-p2.getId();})*/);
        model.addAttribute("zip", zipDocService.getZip(idZ));
        model.addAttribute("viewD","docs");

        return showZips(model);//"all";
    }

    @RequestMapping(method=GET, value="/zips/{idZ}/{idD}" )
    public String showDoc(@PathVariable long idZ, @PathVariable long idD, Model model) {


        model.addAttribute("doc", zipDocService.getDoc(idZ,idD));
        model.addAttribute("viewC","cont");

        return showDocs(idZ,model);//"all";
    }

    @RequestMapping(method=POST, value="/zips/{idZ}")
    //@PathVariable("id") Long id, @ModelAttribute("user") User user,  Model uiModel
    public String addDoc(@PathVariable("idZ") long idZ,@ModelAttribute Doc d,  Model model) {

        zipDocService.addDoc(idZ, d);

        return "redirect:/zips/{idZ}";
    }
}
