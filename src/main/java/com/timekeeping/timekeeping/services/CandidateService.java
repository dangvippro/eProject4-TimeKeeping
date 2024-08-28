package com.timekeeping.timekeeping.services;

import com.timekeeping.timekeeping.models.Candidate;
import com.timekeeping.timekeeping.repositories.CandidateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class CandidateService {

    private static final Logger logger = LoggerFactory.getLogger(CandidateService.class);
    @Autowired
    private CandidateRepository candidateRepository;
    @Value("${file.upload-dir}")
    private String uploadDir;


    public List<Candidate> findAll() {
        return candidateRepository.findAll();
    }

    public Optional<Candidate> findById(int candidateID) {
        return candidateRepository.findById(candidateID);
    }

    public Candidate save(Candidate candidate, MultipartFile resume, MultipartFile profilePicture) throws IOException {
        if (resume != null && !resume.isEmpty()) {
            String resumePath = saveFile(resume);
            candidate.setResume(resumePath);  // Lưu đường dẫn vào Candidate
        }

        if (profilePicture != null && !profilePicture.isEmpty()) {
            String profilePicturePath = saveFile(profilePicture);
            candidate.setProfilePicturePath(profilePicturePath);  // Lưu đường dẫn vào Candidate
        }

        return candidateRepository.save(candidate);
    }

//    private String saveFile(MultipartFile file) throws IOException {
//        String fileName = file.getOriginalFilename();
//        Path path = Paths.get(UPLOAD_DIR + fileName);
//        Files.write(path, file.getBytes());
//        return path.toString();  // Trả về đường dẫn của file đã lưu
//    }

    public void deleteById(int candidateID) {
        candidateRepository.deleteById(candidateID);
    }
    public String saveFile(MultipartFile file) {
        if (file.isEmpty()) {
            logger.warn("Attempted to save an empty file");
            return null;
        }

        try {
            // Create directory if it does not exist
            File uploadDirFile = new File(uploadDir);
            if (!uploadDirFile.exists()) {
                uploadDirFile.mkdirs();
            }

            // Generate a unique file name
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File destinationFile = new File(uploadDirFile, fileName);

            // Save the file to the server
            file.transferTo(destinationFile);

            // Return the path or URL of the saved file
            return fileName; // Adjust to return full path if needed
        } catch (IOException e) {
            logger.error("Failed to save file", e);
            return null;
        }
    }
//    public Candidate save(Candidate candidate) {
//        // Xử lý logic lưu trữ và trả về đối tượng Candidate
//        return candidateRepository.save(candidate);
//    }

}