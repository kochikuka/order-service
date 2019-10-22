package ru.bytebratsk.bytesvc.service;

import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.CommitContext;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import ru.bytebratsk.bytesvc.entity.CurrencyRate;

import javax.inject.Inject;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service(CurrentCurrencyRateService.NAME)
public class CurrentCurrencyRateServiceBean implements CurrentCurrencyRateService {

    @Inject
    private DataManager currencyRateDataManager;

    /* getUsdollarRate() возвращает текущий курс ЦБ РФ US Dollar считанный со страницы http://mfd.ru/ */
    public BigDecimal getUsdollarRate() {
        BigDecimal usRate = null;
        Document mfd_html;


        try {
            // считать и распарсить главную страницу mfd.ru
            mfd_html = Jsoup.connect("http://mfd.ru/").get();

            // ищем курс $ на странице
            Element usd = mfd_html.body().getElementsByAttributeValue("href", "/currency/?currency=USD").first();
            String usRateString = usd.text();
            //Проверяем содержимое тэга на соответствие шаблону
            Pattern p = Pattern.compile("^Курс доллара [\\d]{2}.[\\d]{4}");
            Matcher m = p.matcher(usRateString);
            if(m.matches()) {
                //Выделяем значение курса
                usRate = new BigDecimal(usRateString.replaceAll("[^.\\d]",""));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return(usRate);
    }

    /* getEuroRate() возвращает текущий курс ЦБ РФ EURO считанный со страницы http://mfd.ru/ */
    public BigDecimal getEuroRate() {
        BigDecimal euroRate = null;
        Document mfd_html;


        try {
            // считать и распарсить главную страницу mfd.ru
            mfd_html = Jsoup.connect("http://mfd.ru/").get();

            // ищем курс euro на странице
            Element euro = mfd_html.body().getElementsByAttributeValue("href", "/currency/?currency=EUR").first();
            String euroRateString = euro.text();
            //Проверяем содержимое тэга на соответствие шаблону
            Pattern p = Pattern.compile("^Курс евро [\\d]{2}.[\\d]{4}");
            Matcher m = p.matcher(euroRateString);
            if(m.matches()) {
                //Выделяем значение курса
                euroRate = new BigDecimal(euroRateString.replaceAll("[^.\\d]",""));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return(euroRate);
    }

    /* getAndSaveRate() считывает текущие курсы ЦБ РФ US Dollar, EURO со страницы http://mfd.ru/
    и сохраняет в таблицу CURRENCY_RATE, которая соответствует сущности CurrencyRate */
    @Override
    public void getAndSaveRate() {
        Date currentDate = new Date();
        BigDecimal usdollarRate = getUsdollarRate();
        BigDecimal euroRate = getEuroRate();

        if (usdollarRate != null && euroRate != null) {
            // Создадим новый экземпляр сущности CurrencyRate для текущей даты
            CommitContext commitContext = new CommitContext();
            Metadata metadata = AppBeans.get(Metadata.class);
            CurrencyRate CurrencyRateInstance = metadata.create(CurrencyRate.class);

            // Заполним аттрибуты сущности
            CurrencyRateInstance.setRate_date(currentDate);
            CurrencyRateInstance.setUsdollar_value(usdollarRate);
            CurrencyRateInstance.setEuro_value(euroRate);

            // добавим созданный экземпляр к контексту
            commitContext.addInstanceToCommit(CurrencyRateInstance);

            // Сохраним созданный экземпляр через DataManager в хранилище
            currencyRateDataManager.commit(commitContext);
        }
    }
}