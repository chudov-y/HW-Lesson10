import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class MethodSourceCsvWebTest {

    @BeforeEach
    void setup() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
        open("https://qa.guru");
    }

    static Stream<Arguments> qaGuruTeachersTest() {
        return Stream.of(
                Arguments.of("Курсы Java+", List.of("Дмитрий Тучс Dodo Brands", "Артём Eрошенко Qameta Software", "Владислав Зингер Dodo Engineering")),
                Arguments.of("Курсы Python", List.of("Артём Eрошенко Qameta Software", "Станислав Васенков @qa_automation", "Дмитрий Тучс Dodo Brands", "Александр Котляр", "Сергей Хомутинин", "Яков Крамаренко"))
        );
    }

    @MethodSource
    @ParameterizedTest (name = "В разделе {0} присутствует список преподоавателей {1}")
    @Tag("Teachers")
    void qaGuruTeachersTest(String buttons, List<String> teachers) {
        $(".main-header__menu").$(withText(buttons)).click();
        $$(".titles div").shouldHave(CollectionCondition.texts(teachers));
    }

    @CsvSource( value = {
            "Курсы Java+, Автоматизация тестирования на Java Advanced",
            "Курсы Python, Курс инженеров по автоматизации тестирования на Python"
    })
    @CsvFileSource(resources = "qaGuruTitleTest.csv")
    @ParameterizedTest(name = "В разделе {0} присутствует заголовок {1}")
    @Tag("Titles")
    void qaGuruTitleTest (String buttons, String titles){
        $(".main-header__menu").$(withText(buttons)).click();
        $(".title" ).shouldHave(text(titles));

    }

}