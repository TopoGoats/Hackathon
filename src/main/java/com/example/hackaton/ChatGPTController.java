package com.example.hackaton;
import com.example.hackaton.form.OpenEndedQuestion;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.hackaton.HelloApplication.questions;
import static com.example.hackaton.HelloApplication.traits;

public class ChatGPTController {


    public static String chatGPT(String prompt) {
        String url = "https://api.openai.com/v1/chat/completions";
        String apiKey = "sk-proj-PdsKbIC4bl7NcyrY4wrPT3BlbkFJVV4jMf9g2k2elVcsPyge";
        String model = "gpt-3.5-turbo";

        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setRequestProperty("Content-Type", "application/json");

            // The request body
            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}";
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();

            // Response from ChatGPT
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            StringBuilder response = new StringBuilder();

            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();

            // calls the method to extract the message.
            return extractMessageFromJSONResponse(response.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String extractMessageFromJSONResponse(String response) {
        int start = response.indexOf("content")+ 11;

        int end = response.indexOf("\"", start);

        return response.substring(start, end);

    }

    public static void setupChatGPT()
    {
        String prompt = "Ignore all the previous messages. From now on I want you to only respond in one way: I will be sending you questions about + " +
                "preferences regarding pets as well as answers to those questions. You will also get a list of + " +
                "animals expressed as texts: name and species, as well as a set of parameters from 0 to 10 or expressed as true ar false. I will explain what the lowest and highest values mean in each case:" +
                "- how much care time the animal needs - 0 means almost none, 10 means a lot" +
                "- resourcefulness needed - 0 means the most, 10 means almost none" +
                "- how challenging is the animal for the owner - 0 means the animal is the most challenging, 10 is the least" +
                "- garden size - 0 means the animal doesn't need a garden, 10 means a big garden would be required" +
                "- free time - 0 means the animal doesn't need to much attention, 10 means it needs a lot" +
                "- active lifestyle - 0 means the owner doesn't need to be very active, 10 means it needs a lot of time outside" +
                "- living area - 0 means the animal can live in a loud area like a city center, 10 means it should live in a small, calmer area" +
                "- current animals - 0 means the animal doesn't want to have other animals around, 10 means the more animals in the house, the better" +
                "- house type - 0 means the animal doesn't need a big home, 10 means the bigger house, the better" +
                "- number of housemates - 0 means the animal doesn't want a lot of people in the house, 10 means it doesn't mind" +
                "- children - 0 means the animal doesn't like children, 10 means it does like them" +
                "- animal's activity - 0 means the animal is not very active, 10 means it is very active" +
                "- responsibility - 0 means the animal doesn't need too responsible owner, 10 means it's a great responsibility" +
                "- forgetfulness - 0 means the animal cannot have a forgetfull owner, 10 means it doesn't really matter" +
                "- determined - 0 means the animal doesn't need a determined owner, 10 means it does" +
                "- optimistic - 0 means the animal doesn't need an optimistic owner, 10 means it does" +
                "- has fur - true - has, false - doesn't" +
                "Based on this data I want you to decide which animals would suit a person that answered above questions. +" +
                "In answer to next messages write all the names of animals that you think fit the person that answered the questions +" +
                "wrote on the beginning of the messages. I want you to only write, which animals would fit the person who answered the questions. +" +
                "So if the animal 1 fits the person's preferences, write 'animal 1'. if it doesn't, than don't write it and so on. And write only the fitting names, not one word more.";


        System.out.println(chatGPT(prompt));
    }
    public static void interpretOpenEndQuestions() {
        StringBuilder prompt = new StringBuilder();
        for (int j = 18; j < questions.size(); j++) {
            if(questions.get(j) instanceof OpenEndedQuestion question) {
                prompt.append(" Q: ").append(question.getQuestion());
                prompt.append("A: ").append(question.getActualAnswer()).append(" ");
            }
        }
        prompt.append(" - how much care time the animal needs - 0 means almost none, 10 means a lot - ").append(traits.get("careTimeNeeded"));
        prompt.append(" - resourcefulness needed - 0 means the most, 10 means almost none").append(traits.get("resourcefulness"));
        prompt.append(" - how challenging is the animal for the owner - 0 means the animal is the most challenging, 10 is the least").append(traits.get("competentWithAnimals"));
        prompt.append(" - how impulsive can the owner be - 0 means the owner can't be impulsive, 10 means the owner can be impulsive").append(traits.get("impulsiveness"));
        prompt.append(" - how much should the owner make - 0 means the owner should could as little as possible, 10 means the owner should make as much as possible").append(traits.get("income"));
        prompt.append(" - garden size - 0 means the animal doesn't need a garden, 10 means a big garden would be required - ").append(traits.get("gardenSize"));
        prompt.append(" - free time - 0 means the animal doesn't need to much attention, 10 means it needs a lot - ").append(traits.get("freeTime"));
        prompt.append(" - active lifestyle - 0 means the owner doesn't need to be very active, 10 means it needs a lot of time outside - ").append(traits.get("activeLifestyle"));
        prompt.append(" - living area - 0 means the animal can live in a loud area like a city center, 10 means it should live in a small, calmer area - ").append(traits.get("livingArea"));
        prompt.append(" - current animals - 0 means the animal doesn't want to have other animals around, 10 means the more animals in the house, the better - ").append(traits.get("currentAnimals"));
        prompt.append(" - house type - 0 means the animal doesn't need a big home, 10 means the bigger house, the better - ").append(traits.get("houseType"));
        prompt.append(" - number of housemates - 0 means the animal doesn't want a lot of people in the house, 10 means it doesn't mind - ").append(traits.get("housemateCount"));
        prompt.append(" - how old should be the questionaree - 0 means the questionaree should be young, 10 means the questionaree should be old - ").append(traits.get("qustionareeAge"));
        prompt.append(" - children - 0 means the animal doesn't like children, 10 means it does like them - ").append(traits.get("children"));
        prompt.append(" - animal's activity - 0 means the animal is not very active, 10 means it is very active - ").append(traits.get("animalsActivity"));

        System.out.println(chatGPT(prompt.toString()));
    }




}
