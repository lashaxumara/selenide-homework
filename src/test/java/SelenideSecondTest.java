import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.testng.ScreenShooter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Listeners({ScreenShooter.class})
public class SelenideSecondTest {

    @BeforeClass
    public void setUp(){
        Configuration.baseUrl = "https://demoqa.com/books";
        Configuration.browserSize = "1920x1080";
        Configuration.reportsFolder = "src/main/resources/Reports";
        Configuration.savePageSource = false;
    }

    @Test
    public void bookListAssertions() {
        open("");
        SoftAssert softAssert = new SoftAssert();

        ElementsCollection titles = $(".rt-table").find(".rt-tbody").findAll(".rt-tr-group").filter(Condition.text("O'Reilly Media")).filter(Condition.text("JavaScript"));
        softAssert.assertEquals(titles.size(), 10);
        titles.first().should(Condition.text("Learning JavaScript Design Patterns"));

        titles.stream().forEach(el->{
            el.find("div[class='action-buttons']").click();
            $("#addNewRecordButton").scrollTo().click();
            el.scrollTo();
        });

        softAssert.assertAll();
    }

    @Test
    public void innerTest(){
        open("");

        $(".rt-tbody").$$(".rt-tr-group").filter(Condition.text("O'Reilly Media")).filter(Condition.text("JavaScript"));
        ElementsCollection imgEls =  $(".rt-tbody").$(".rt-tr-group").$$("img[alt='image']");
        for (SelenideElement imgEl : imgEls){
            imgEl.shouldBe(visible);
        }

    }
}
