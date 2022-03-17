import static com.constants.ProjectConstants.CONTAINER_NAME;
import static com.container.containerHelper.*;


import org.junit.Test;

import java.io.IOException;

public class downLoadUploadTest
{
    @Test
    public void downLoadFiles() throws IOException {

//        icContainerAvailable(CONTAINER_NAME);
        listFilesInContainer(CONTAINER_NAME);
//        downloadFiles(CONTAINER_NAME);
//        uploadFile(CONTAINER_NAME, "jmeterResults.jpg");
        downloadFile(CONTAINER_NAME, "userdata1.parquet");
        readFile( CONTAINER_NAME, "AEM.txt");
//        readParquetFile( );

    }

}
