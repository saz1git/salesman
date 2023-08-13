package com.example.sales.web;
import com.example.sales.entities.Sales;
import com.example.sales.repositories.SalesRepository;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;
public class SalesController {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  @Autowired
    private SalesRepository salesRepository;
    static int num=0;


    @GetMapping(path="/index")
    public String sales(Model model,@RequestParam(name="keyword",defaultValue="") String keyword){
        List<Sales> sales;
        if(keyword.isEmpty()){
            sales=salesRepository.findAll();
        }else{
            long key = Long.parseLong(keyword);
            sales = salesRepository.findSalesById(key);
        }
        model.addAttribute("listSales",sales);
        return "sales";
    }
    @GetMapping("/delete")
    public String delete(Long id){
        salesRepository.deleteById(id);
        return "redirect:/index";
    }
    @GetMapping("/formSales")
    public String formSales(Model model){
        model.addAttribute("salesman", new Sales());
        return "formSales";
    }
    @PostMapping(path="/save")
    public String save(Model model, Sales sales, BindingResult bindingResult, ModelMap mm, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "formSales";
        } else {
            salesRepository.save(sales);
            if (num == 2) {
                mm.put("e", 2);
                mm.put("a", 0);
            } else {
                mm.put("a",1);
                mm.put("e",0);
            }
            return "redirect:index";
        }
    }
    @GetMapping("/editSales")
    public String editSales(Model model, Long id, HttpSession session){
        num = 2;
        session.setAttribute("info", 0);
        Sales sales = salesRepository.findById(id).orElse(null);
        if(sales==null) throw new RuntimeException("Does not exist");
        model.addAttribute("sales", sales);
        return "editSales";
    }
    @GetMapping(path = "/")
    public String sales2(Model model, ModelMap mm,
                            @RequestParam(name="keyword",defaultValue = "") String
                                    keyword,HttpSession session){
        List<Sales> sales;
        if (keyword.isEmpty()) {
            sales = salesRepository.findAll();
        } else {
            mm.put("e", 0);
            mm.put("a", 0);
            long key = Long.parseLong(keyword);
            sales = salesRepository.findSalesById(key);
        }
        model.addAttribute("listSales", sales);
        return "sales";
    }
}