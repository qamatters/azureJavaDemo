package com.container;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobItem;
import org.apache.commons.lang3.StringUtils;
import java.io.*;

import static com.azureDB.KeyDontAddMeInGit.YOUR_KEY;
import static com.constants.ProjectConstants.*;


public class containerHelper {
    static String localPath = "./data/";
    static String uploadPath = "data\\upload\\";
    static String downloadedPath = "data\\downloaded\\";
    static String fileName = "quickstart" + java.util.UUID.randomUUID() + ".txt";
    public static String createConnectionString() {
        String connectStr = "DefaultEndpointsProtocol="+DEFAULT_PROTOCOL+";AccountName="+ ACCOUNT_NAME+";AccountKey="+YOUR_KEY+";EndpointSuffix="+END_POINT_SUFFIX+"";
         return connectStr;
    }

    public static BlobServiceClient createBlobServiceClient() {
        String connectStr = createConnectionString();
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(connectStr).buildClient();
        return blobServiceClient;
    }

    // Create a BlobServiceClient object which will be used to create a container client
    public static BlobContainerClient createContainer(String containerName) {
        BlobServiceClient blobServiceClient = createBlobServiceClient();
        BlobContainerClient containerClient = blobServiceClient.createBlobContainer(containerName);
        return containerClient;
    }

    public static void createAfILe() throws IOException {
        File localFile = new File(localPath + fileName);
        FileWriter writer = new FileWriter(localPath + fileName, true);
        writer.write("Hello, World!");
        writer.close();
    }

    public static void icContainerAvailable(String containerName) {
        BlobServiceClient blobServiceClient = createBlobServiceClient();
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
        if (containerClient.exists()) {
            System.out.println("Container " + containerName + " is available");
        } else {
            System.out.println("Container " + containerName + " does not exist.");
        }
    }

    public static void listFilesInContainer(String containerName) {
        BlobServiceClient blobServiceClient = createBlobServiceClient();
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
        for (BlobItem blobItem : containerClient.listBlobs()) {
            System.out.println("\t" + blobItem.getName());
        }
    }

        public static void downloadFiles(String containerName) {
            BlobServiceClient blobServiceClient = createBlobServiceClient();
            BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
            for (BlobItem blobItem : containerClient.listBlobs()) {
                BlobClient blobClient = containerClient.getBlobClient(blobItem.getName());
                File downloadedFile = new File(downloadedPath + blobItem.getName());
                System.out.println("\nDownloading blob to\n\t " +  downloadedFile);
                blobClient.downloadToFile(downloadedFile.getPath());
            }
        }

    public static void downloadFile(String containerName, String fileName) {
        String fileFromContainer = "";
        BlobServiceClient blobServiceClient = createBlobServiceClient();
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
        for (BlobItem blobItem : containerClient.listBlobs()) {
            if(blobItem.getName().contains("/")) {
                fileFromContainer = StringUtils.substringAfterLast(blobItem.getName(), "/");
            } else {
                fileFromContainer = blobItem.getName();
            }

            try {
                if (fileFromContainer.contains(fileName)) {
                    BlobClient blobClient = containerClient.getBlobClient(blobItem.getName());
                    File downloadedFile = new File(downloadedPath + fileFromContainer);
                    System.out.println("\nDownloading blob to\n\t " + downloadedFile);
                    blobClient.downloadToFile(downloadedFile.toString());
                }
            } catch (Exception e) {
            }
        }
    }

        public static void deleteContainer(String containerName) {
            BlobServiceClient blobServiceClient = createBlobServiceClient();
            BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
            containerClient.delete();
        }

        public static void uploadFile(String containerName, String fileName) {
            BlobServiceClient blobServiceClient = createBlobServiceClient();
            BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
            BlobClient blobClient = containerClient.getBlobClient(fileName);
            System.out.println("\nUploading to Blob storage as blob:\n\t" + blobClient.getBlobUrl());
            blobClient.uploadFromFile(uploadPath + fileName);
        }


    public static void readFile(String containerName, String fileName) throws IOException {
        BlobServiceClient blobServiceClient = createBlobServiceClient();
        BlobContainerClient containerClient =blobServiceClient.getBlobContainerClient(containerName);
        BlobClient blob = containerClient.getBlobClient(fileName);
        BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(blob.openInputStream()));
        String line;
        while ((line = bufferedreader.readLine()) != null)
        {
            System.out.println(line);

        }

    }


        public static void readParquetFile() {
//            readParquet();

        }


}
