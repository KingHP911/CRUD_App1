package ru.kinghp.crudapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kinghp.crudapp.dao.PersonDAO;
import ru.kinghp.crudapp.models.Person;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private PersonDAO personDAO;

    @GetMapping()
    public String index(Model model){
        // Получим всех людей из dao и передадим на отображение в представление
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        // Получим одного человека из DAO и передадим на отображение в представление
        model.addAttribute("person", personDAO.show(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person){
        //model.addAttribute("person", new Person()); эквиваленитно @ModelAttribute("person") Person person с model в аргументе newPerson()
        return "people/new";
    }

    @PostMapping()
//    public String create(@ModelAttribute("person") Person person){
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult){
        //@Valid проверяет корректность значений
        //BindingResult bindingResult ошибка валидации. Должен быть после валидир-го объекта
        if (bindingResult.hasErrors()){
            return "people/new";
        }
        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
//    public String update(@ModelAttribute("person") Person person, @PathVariable("id") int id){
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, @PathVariable("id") int id){

        if (bindingResult.hasErrors()){
            return "people/edit";
        }

        personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        personDAO.delete(id);
        return "redirect:/people";
    }
}
