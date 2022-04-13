import com.codeborne.selenide.Condition;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryCardTest {

  
    @Test
    void shouldSuccessfulFormSubmission() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Москва");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
        String verificationDate = LocalDate.now() .plusDays(3)
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").setValue(verificationDate);
        $("[name='name']").setValue("Ильнур Мамешев");
        $("[name='phone']").setValue("+79000000000");
        $("[data-test-id=agreement]").click();
        $x("//*[text()=\"Забронировать\"]").click();
        $("[data-test-id=notification]")
                .shouldHave(Condition.text("Успешно! Встреча успешно забронирована на " + verificationDate),
                        Duration.ofSeconds(15));
    }


    @Test
    void shouldFailCity() {
        open("http://localhost:9999");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
        String verificationDate = LocalDate.now() .plusDays(3)
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").setValue(verificationDate);
        $("[name='name']").setValue("Ильнур Мамешев");
        $("[name='phone']").setValue("+79000000000");
        $("[data-test-id=agreement]").click();
        $x("//*[text()=\"Забронировать\"]").click();
        $("[data-test-id=city]")
                .shouldHave(Condition.text("Поле обязательно для заполнения"));

    }

    @Test
    void shouldFail() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Москва");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
        String verificationDate = LocalDate.now() .plusDays(3)
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").setValue(verificationDate);
        $("[name='name']").setValue("Ilnur Mameshev");
        $("[name='phone']").setValue("+79000000000");
        $("[data-test-id=agreement]").click();
        $x("//*[text()=\"Забронировать\"]").click();
        $("[data-test-id=name]")
                .shouldHave(Condition.text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    void shouldFailPhone() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Москва");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
        String verificationDate = LocalDate.now() .plusDays(3)
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").setValue(verificationDate);
        $("[name='name']").setValue("Ильнур Мамешев");
        $("[name='phone']").setValue("79000000000");
        $("[data-test-id=agreement]").click();
        $x("//*[text()=\"Забронировать\"]").click();
        $("[data-test-id=phone]")
                .shouldHave(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

    @Test
    void shouldFailCheckBox() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Москва");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
        String verificationDate = LocalDate.now() .plusDays(3)
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").setValue(verificationDate);
        $("[name='name']").setValue("Ильнур Мамешев");
        $("[name='phone']").setValue("+79000000000");
        $x("//*[text()=\"Забронировать\"]").click();
        $("[data-test-id=agreement]")
                .shouldHave(Condition.text("Я соглашаюсь с условиями обработки и использования моих персональных данных"));

    }
// Необязательная задача
    @Test
    void shouldSuccessfulFormSubmissionOnWeekend() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Мо");
        $(byText("Москва")).click();
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
        String verificationDate = LocalDate.now() .plusDays(7)
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").setValue(verificationDate);
        $("[name='name']").setValue("Ильнур Мамешев");
        $("[name='phone']").setValue("+79000000000");
        $("[data-test-id=agreement]").click();
        $x("//*[text()=\"Забронировать\"]").click();
        $("[data-test-id=notification]")
                .shouldHave(Condition.text("Успешно! Встреча успешно забронирована на " + verificationDate),
                        Duration.ofSeconds(15));
    }
}
