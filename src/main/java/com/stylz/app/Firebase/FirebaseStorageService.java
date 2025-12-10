
package com.stylz.app.Firebase;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class FirebaseStorageService {

    public static String uploadOutfitSnapshot(WritableImage snapshot, String userId) throws IOException {
        if (snapshot == null) {
            return null;
        }

        // WritableImage -> PNG bytes
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(snapshot, null);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", baos);
        byte[] data = baos.toByteArray();

        // default bucket
        Bucket bucket = StorageClient.getInstance().bucket();
        if (bucket == null) {
            throw new IllegalStateException("Firebase Storage bucket is not configured.");
        }

        String safeUserId = (userId != null ? userId : "anonymous");
        String fileName = "outfits/" + safeUserId + "/" + System.currentTimeMillis() + ".png";

        Blob blob = bucket.create(fileName, data, "image/png");

        String encodedPath = URLEncoder.encode(fileName, StandardCharsets.UTF_8);
        String bucketName = bucket.getName();

        return "https://firebasestorage.googleapis.com/v0/b/"
                + bucketName
                + "/o/"
                + encodedPath
                + "?alt=media";
    }
}

