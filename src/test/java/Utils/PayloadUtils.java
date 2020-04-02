package Utils;

public class PayloadUtils {

    public static String getPetPayload(Integer id){

        String requestBody = "{\n" +
                "    \"id\":\""+id+"\",\n" +
                "    \"category\": {\n" +
                "        \"id\": 2,\n" +
                "        \"name\": \"shepard\"\n" +
                "    },\n" +
                "    \"name\": \"Reks\",\n" +
                "    \"photoUrls\": [\n" +
                "        \"https://s3.amazon.com/myPet.jpg\"\n" +
                "    ],\n" +
                "    \"tags\": [\n" +
                "        {\n" +
                "            \"id\": 3,\n" +
                "            \"name\": \"string\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"status\": \"not available\"\n" +
                "}";

        return requestBody;
    }

    public static String getUserPayLoad(String name, String job){
        String requestBody = "{\n" +
                "    \"name\": \""+name+"\",\n" +
                "    \"job\": \""+job+"\"\n" +
                "}";

        String ex = "{\n" +
                "    \"name\": \"Norah\",\n" +
                "    \"job\": \"singer\"\n" +
                "}";

        return requestBody;
    }

    public static String getAuthorizationPayload(){
        String requesBody = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"cityslicka\"\n" +
                "}";

        return requesBody;
    }

    public static String getJiraPayload(String key,String assignee,String summary,
                                        String description, String priority,String issueType ){
        return "{\n" +
                "    \"fields\": {\n" +
                "        \"project\": {\n" +
                "            \"key\": \""+key+"\"\n" +
                "        },\n" +
                "        \"assignee\": {\n" +
                "            \"name\": \""+assignee+"\"\n" +
                "        },\n" +
                "        \"summary\": \""+summary+"\",\n" +
                "        \"description\": \""+description+"\",\n" +
                "        \"issuetype\": {\n" +
                "            \"name\": \""+issueType+"\"\n" +
                "        },\n" +
                "        \"priority\": {\n" +
                "            \"name\": \""+priority+"\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
    }

    public static  String getCredentials(){
        String postBody = "{\n" +
                "    \"username\": \""+ ConfigReader.getProperty("jiraUsername") +"\",\n" +
                "    \"password\": \""+ ConfigReader.getProperty("jiraPassword")+"\"\n" +
                "}";
        return postBody;
    }

    public static String getSprint(String sprintName, int originBoardId){
        String sprintBody = "{\n" +
                "  \"name\": \""+sprintName+"\",\n" +
                "  \"startDate\": \"2020-04-11T15:22:00.000+10:00\",\n" +
                "  \"endDate\": \"2020-04-25T15:22:00.000+10:00\",\n" +
                "  \"originBoardId\": "+originBoardId+",\n" +
                "  \"goal\": \"Sprintgoal\"\n" +
                "}";

        return sprintBody;
    }
}
