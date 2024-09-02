import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Usuario {
    private String email;
    private String senha;

    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean realizaLogin(String email, String senha) {
        String csvFile = "src/main/resources/usuarios.csv";
        String linha;
        String separador = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine();

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(separador);
                String emailCsv = dados[0];
                String senhaCsv = dados[1];

                System.out.println(emailCsv);
                System.out.println(email);

                if (email.equals(emailCsv) && senha.equals(senhaCsv)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    private String extractValue(String jsonString, String key) {
        String keyValuePattern = "\"" + key + "\":\"";
        int startIndex = jsonString.indexOf(keyValuePattern);
        if (startIndex == -1) {
            return null;
        }
        startIndex += keyValuePattern.length();
        int endIndex = jsonString.indexOf("\"", startIndex);
        return jsonString.substring(startIndex, endIndex);
    }

    public String getNome() {
        try {
            FileReader reader = new FileReader("src/main/resources/db.json");
            BufferedReader bufferedReader = new BufferedReader(reader);
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line);
            }
            reader.close();

            String contentString = content.toString();
            String[] users = contentString.split("\\},\\{");

            for (String user : users) {
                String email = extractValue(user, "email");
                if (this.getEmail().equals(email)) {
                    return extractValue(user, "nome");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
