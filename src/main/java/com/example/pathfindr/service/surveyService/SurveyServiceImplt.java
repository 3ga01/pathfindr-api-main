// package com.example.pathfindr.service.surveyService;

// import java.util.ArrayList;
// import java.util.List;

// import org.springframework.stereotype.Service;

// import com.example.pathfindr.model.SurveyResponse;

// @Service
// public class SurveyServiceImplt implements SurveyService {

//     @Override
//     public List<String> processSurvey(List<Integer> surveyAnswers) {

//         String[] fieldNames = {
//                 "Engineering",
//                 "Business Analysis",
//                 "Medicine",
//                 "Art",
//                 "Social Sciences",
//                 "Humanities",
//                 "Information Technology"
//         };

//         // Calculate the scores for each field based on user's choices
//         int[] scores = new int[fieldNames.length];

//         // Process the survey answers and increment scores
//         for (int answer : surveyAnswers) {
//             scores[answer]++;
//         }

//         // Find the top three fields
//         List<String> topFields = new ArrayList<>();
//         for (int i = 0; i < 3; i++) {
//             int maxScore = -1;
//             int maxIndex = -1;

//             for (int j = 0; j < scores.length; j++) {
//                 if (scores[j] > maxScore) {
//                     maxScore = scores[j];
//                     maxIndex = j;
//                 }
//             }

//             if (maxIndex != -1) {
//                 topFields.add(fieldNames[maxIndex]);
//                 scores[maxIndex] = -1; // Mark as visited
//             }
//         }

//         return topFields;

//     }

// }
