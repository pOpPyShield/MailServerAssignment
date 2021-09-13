package ServerAndClient;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.net.URI;

public class FileCustom extends File {

    private String pathName;
    public FileCustom(String pathname) {
        super(pathname);
        this.pathName = pathname;
    }

    public FileCustom(String parent, String child) {
        super(parent, child);
    }

    public FileCustom(File parent, String child) {
        super(parent, child);
    }

    public FileCustom(URI uri) {
        super(uri);
    }

    @Override
    public String toString() {
        return getFileNameWithoutExtension();
    }
    private String getFileNameWithoutExtension() {

        String name = this.pathName;
        String fileName = FilenameUtils.getBaseName(name);
        return fileName;

    }

}
