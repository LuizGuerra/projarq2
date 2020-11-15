package src.model;

public enum ConnectionStatus {
    successful, badLogin, badPassword, badName;

    public String toMessage(String withEcommerceName) {
        return switch (this) {
            case successful -> "Connection to " + withEcommerceName + " was successful\n";
            case badLogin -> "Incorrect login for " + withEcommerceName + "\n";
            case badPassword -> "Incorrect password for " + withEcommerceName + "\n";
            case badName -> "No ecommerce found with name " + withEcommerceName + "\n";
        };
    }
}
