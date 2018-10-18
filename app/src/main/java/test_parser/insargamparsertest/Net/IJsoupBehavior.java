package test_parser.insargamparsertest.Net;

import org.jsoup.nodes.Document;

/**
 * Интерфейс подключает возможность использования Jsop через класс HtmlDownloader
 * @see HtmlDownloader
 */
public interface IJsoupBehavior {
    /**
     * Срабатывает после успешной загрузки Html
     * @param doc параметр, содержащий Html в формате org.jsoup.nodes.Document
     * @param loader загрузчик, который был использован для загрузки html
     */
    void onLoadedJsoup(HtmlDownloader loader, Document doc);

    /**
     * Срабатывает при ошибки загрузки Html
     * @param e информация об ошибке
     * @param loader загрузчик, который был использован для загрузки html
     */
    void onException(HtmlDownloader loader, Exception e);
}
