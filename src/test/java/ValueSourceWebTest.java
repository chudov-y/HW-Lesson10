import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ValueSourceWebTest {

    @BeforeEach
    void setup() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";

        open("/elements");

        $(".main-header").shouldHave(text("Elements"));
    }


    @ParameterizedTest
    @DisplayName("При клике на элемент в боковом меню открывается нужный тренажер")
    @ValueSource(strings = {"Text Box", "Check Box", "Radio Button", "Web Tables"})
    @Tag("Elements")
    void successfulClickTest (String link){
        $(".menu-list").$(withText(link)).click();
        $(".main-header").shouldHave(text(link));

    }

}
