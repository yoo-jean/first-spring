package first.firstspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // body부에 직접 넣겠다
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // "hello kim"    view 없음
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // json type으로 출력 {"name":"spring!!!!"}

    }

    static class Hello{
        private String name;
        // 단축키 : command + n
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
