import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;


public class SelenideTests {
    @Test
    public void checkbox() {
        open("http://the-internet.herokuapp.com/checkboxes");

        ElementsCollection checkboxes = $$("input[type='checkbox");
        checkboxes.get(0).setSelected(true);
        for (SelenideElement element : checkboxes) {
            element.shouldHave(type("checkbox"));
        }
    }

    @Test
    public void dropDown() {
        open("http://the-internet.herokuapp.com/dropdown");

        SelenideElement dropdown = $("#dropdown");
        dropdown.getSelectedOption().shouldHave(text("Please select an option"));
        dropdown.selectOption(2);
        dropdown.shouldHave(text("Option 2"), value("2"));
    }

    @Test
    public void fieldFill() {
        open("https://demoqa.com/text-box");

        $(byId("userName")).setValue("Lasha Khumarashvili");
        $(byAttribute("type","email")).setValue("lashaxumara@gmail.com");
        $(By.xpath("//textarea[@placeholder='Current Address']")).setValue("Varketili");
        $(("#permanentAddress.form-control")).setValue("Tbilisi");

        SelenideElement submit = $("#submit");
        submit.scrollTo().click();

        ElementsCollection fields = $$(By.xpath("//p[@id]"));
        fields.shouldHave(exactTexts("Name:Lasha Khumarashvili", "Email:lashaxumara@gmail.com", "Current Address :Varketili", "Permananet Address :Tbilisi"));
    }
}
