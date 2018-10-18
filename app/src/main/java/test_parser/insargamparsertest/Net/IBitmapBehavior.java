package test_parser.insargamparsertest.Net;

import android.graphics.Bitmap;

public interface IBitmapBehavior {
    /**
     * Срабатывает после успешной загрузки Html
     * @param loadedBitmap загруженные данные в формате Bitmap
     * @param loader загрузчик, который был использован для загрузки html
     */
    void onLoadedImage(ImageDownloader loader, Bitmap loadedBitmap); //TODO универсальный загрузчик

    /**
     * Срабатывает при ошибки загрузки Html
     * @param e информация об ошибке
     * @param loader загрузчик, который был использован для загрузки html
     */
    void onException(ImageDownloader loader, Exception e);
}
