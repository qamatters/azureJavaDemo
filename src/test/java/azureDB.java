import org.junit.Test;
import java.io.IOException;

import static com.azureDB.dbHelper.readDBWithUsernameAndPassword;


public class azureDB {

    @Test
    public void downLoadFiles() throws IOException {
        readDBWithUsernameAndPassword();

    }

}