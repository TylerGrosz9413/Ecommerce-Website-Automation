package resources;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;

public class AdBlockAutoDownload {
     /**
     * Downloads a Chrome extension CRX file given its extension ID.
     * @param extensionId The Chrome Web Store extension ID (e.g., "cjpalhdlnbpafiamejdnhcphjbkeiagm" for uBlock Origin)
     * @param downloadPath Path where the CRX file will be saved
     * @throws IOException if download fails
     */
    public static void downloadCrx(String extensionId, Path downloadPath) throws IOException {
        // Chrome extension CRX download URL format
        String crxUrl = String.format(
                "https://clients2.google.com/service/update2/crx?response=redirect&prodversion=91.0&x=id%%3D%s%%26installsource%%3Dondemand%%26uc",
                extensionId
        );
        // HttpURLConnection connection = (HttpURLConnection) new URI(crxUrl).openConnection();
        // connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        // connection.connect();
    }

}
