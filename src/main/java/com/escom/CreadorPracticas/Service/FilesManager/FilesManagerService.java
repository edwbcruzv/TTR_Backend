package com.escom.CreadorPracticas.Service.FilesManager;

import com.escom.CreadorPracticas.Exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FilesManagerService {

    @Value("${user.root.directory}")
    private String rootDirectory;

    private List<String> permitedTypes = Arrays.asList(
            "image/jpeg", "image/png", "image/gif", "image/bmp", "image/webp",
            "audio/mpeg", "audio/wav", "audio/ogg", "audio/midi",
            "video/mp4", "video/webm", "video/quicktime",
            "application/pdf", "application/msword", "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
            "application/vnd.ms-excel", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
            "application/vnd.ms-powerpoint", "application/vnd.openxmlformats-officedocument.presentationml.presentation"

    );
    private String prepareSavePath(String folderName) {
        // Obtiene el separador de ruta según el sistema operativo
        String separator = File.separator;

        // Construye la ruta completa
        String pathToSave = rootDirectory + separator + "StaticFiles"  + separator + folderName + separator;

        // Verifica la existencia del directorio, si no existe, créalo
        File directory = new File(pathToSave);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        return pathToSave;
    }

    private String generateFileName(Long usuarioId, Long caseId, String extension) {
        // Implementa la lógica para generar un nombre de archivo único
        return "multimedia_" + usuarioId + "_" + caseId + "_" + System.currentTimeMillis() + "." + extension;
    }

    private String saveFile(MultipartFile file, String pathToSave, String fileName) throws IOException {
        // Guarda el archivo en el servidor
        String filePath = pathToSave + fileName;
        file.transferTo(new File(filePath));

        return filePath;
    }

    public String saveMultimedia(MultipartFile file, Long usuarioId, Long caseId) throws BadRequestException, IOException {


        if(!isTypeValid(file))
            throw new BadRequestException();

        // Obtén la extensión del archivo original
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf('.') + 1);

        // Prepara la ruta de almacenamiento
        String folderName = "multimedia"; // Puedes personalizar el nombre de la carpeta
        String pathToSave = prepareSavePath(folderName);

        // Genera un nombre de archivo único
        String fileName = generateFileName(usuarioId, caseId, extension);

        // Guarda el archivo y devuelve la ruta del archivo guardado
        return saveFile(file, pathToSave, fileName);



    }


    public Boolean updateMultimedia(String rutaCompleta, MultipartFile nuevoArchivo) throws IOException {
        // Obtén el archivo existente
        File archivoExistente = new File(rutaCompleta);

        // Verifica si el archivo existe antes de intentar actualizarlo
        if (archivoExistente.exists()) {
            // Convierte el MultipartFile a un array de bytes
            byte[] bytesNuevos = nuevoArchivo.getBytes();

            // Utiliza FileOutputStream para escribir los nuevos bytes en el archivo existente
            try (FileOutputStream fileOutputStream = new FileOutputStream(archivoExistente)) {
                FileCopyUtils.copy(bytesNuevos, fileOutputStream);
            }

            System.out.println("Archivo actualizado con éxito.");
            return true;
        } else {
            System.out.println("El archivo no existe en la ruta especificada: " + rutaCompleta);
            return false;
        }
    }

    public Boolean deleteMultimedia(String rutaCompleta) {
        // Crea un objeto File con la ruta del archivo
        File archivo = new File(rutaCompleta);

        // Verifica si el archivo existe antes de intentar eliminarlo
        if (archivo.exists()) {
            // Intenta eliminar el archivo
            if (archivo.delete()) {
                System.out.println("Archivo eliminado con éxito.");
                return true;
            } else {
                System.out.println("No se pudo eliminar el archivo.");
                return false;
            }
        } else {
            System.out.println("El archivo no existe en la ruta especificada: " + rutaCompleta);
            return false;
        }
    }

    public Boolean isTypeValid(MultipartFile file) {
        System.out.println(file.getContentType());
        return permitedTypes.contains(file.getContentType());
    }
}
