package src.model;

public enum ConnectionStatus {
    successful, badLogin, badPassword, badName, alreadyConnected;

    public String toMessage(String withEcommerceName) {
        switch (this) {
            case successful: return "Connection to " + withEcommerceName + " was successful\n";
            case badLogin: return "Incorrect login for " + withEcommerceName + "\n";
            case badPassword: return "Incorrect password for " + withEcommerceName + "\n";
            case badName: return "No ecommerce found with name " + withEcommerceName + "\n";
            case alreadyConnected: return "Already connected to " + withEcommerceName + ".\n";
        };
        return "";
    }
}
