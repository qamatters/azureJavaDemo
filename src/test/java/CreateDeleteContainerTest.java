import org.junit.Test;

import static com.container.containerHelper.*;

public class CreateDeleteContainerTest
{
    @Test
    public void containerTest() {
        String containerName = "testcontainerdeepak";
//        createContainer(containerName);
        icContainerAvailable(containerName);
        listFilesInContainer(containerName);
        deleteContainer(containerName);
        icContainerAvailable(containerName);
    }
}
