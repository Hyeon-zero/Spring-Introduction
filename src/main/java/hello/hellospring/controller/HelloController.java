package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // Controller는 화면(View)과 비즈니스 로직(Model)를 연결시키는 다리 역할을 한다.
// 쉽게 말하자면 화면에서 /aaa 로 가줘~ 하고 요청하면 주소를 받아들여 어디로 갈지 분석하고 맞는 길로 연결시켜주는 역할이다.
public class HelloController {

    // 정적 컨텐츠
    @GetMapping("hello") // 웹 어플리케이션에서 /hello가 들어오면 아래 메서드를 호출해준다.
    // HTTP GET 요청을 특정 핸들러 메소드에 맵핑하기위한 Annotation
    public String hello(Model model){ // Spring이 model를 만들어 넣어준다.
        model.addAttribute("data", "hello!!");

        return "hello"; // resources-templates-hello.html 파일로 가서 Rendering 한다.(Thymeleaf 템플릿 엔진 처리)
        // Rendering : 웹브라우저가 읽어들인 웹 문서 내용(코드)를 웹 표준에 맞도록 화면에 적절히 그려내는 중요 구성요소.
    }

    // MVC와 템플릿 엔진
    @GetMapping("hello-mvc")
    public String helloMVC(@RequestParam("name") String web, Model model) {
        // @RequestParam 어노테이션은 Spring Framework에서 HTTP 요청의 매개변수(parameter)를 컨트롤러 메소드의 매개변수와 연결해준다.
        // 웹브라우저에 http://localhost:8080/hello-mvc?name=spring 입력시 "spring"이 web 매개변수에 전달된다.
        // Model 매개변수는 view에 데이터를 전달하기 위한 객체이다.
        model.addAttribute("name", web); // key : name, value : web 값을 Model에 추가한다.
        return "hello-template"; // resources-templates에 있는 hello-template.html로 이동한다.
    }

    // API
    @GetMapping("hello-string")
    @ResponseBody // http에 있는 body 부분에 직접 데이터를 넣어주겠다는 의미이다.
    // return "hello" + web; 코드가 데이터를 직접 넣어준다는 의미이다.
    public String helloString(@RequestParam("name") String web) {
        return "hello " + web; // "hello spring" 데이터를 그대로 내려준다.
        // resources-templates에 있는 html 파일로 이동하는 것이 아니라 웹 사이트 창에 "hello " + web 내용을 보여준다.
        // 예) web = "SpringFramework" 이면 => hello SpringFramework 내용을 보여준다.
    }

    @GetMapping("hello-api")
    @ResponseBody // http에 있는 body 부분에 return "hello" + name; 코드가 데이터를 직접 넣어준다는 의미이다.
    public Hello helloApi(@RequestParam("name") String web) { // @ResponseBody 를 사용하고, 객체를 반환하면 객체가 JSON으로 변환된다.
        Hello hello = new Hello(); // Hello 객체 생성
        hello.setName(web);
        return hello; // 참조변수를 return 시키면 JSON 형식으로 나온다.
        // 예) web = "spring" 이면 {"name":"spring"} 으로 출력된다.
        /*
          일반적으로 JSON 데이터를 생성할 때는 공백을 추가하지 않는 것이 좋다.
          왜냐하면 공백이 추가될 때마다 전송되는 데이터의 크기가 증가하므로, 전송 속도가 느려지기 때문이다.
        */
    }
    static class Hello {
        private String name;

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}