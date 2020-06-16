package lozm.global.props;

import lombok.extern.slf4j.Slf4j;
import lozm.global.exception.ServiceException;

import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class CommonProps {

	public Path getSourcePath(String path) {
		return Paths.get(path)
				.toAbsolutePath()
				.normalize();
	}

	public void createFilesDirectories(Path path) {
		try {
			Files.createDirectories(path);
		} catch (FileAlreadyExistsException faee) {
			log.debug(faee.getMessage());
			return;
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new ServiceException("FILE001002", e.getMessage());
		}
	}

}
