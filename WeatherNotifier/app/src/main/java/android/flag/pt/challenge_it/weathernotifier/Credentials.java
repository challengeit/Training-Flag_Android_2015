package android.flag.pt.challenge_it.weathernotifier;

/**
 * Classe modelo que define credenciais.
 *
 * @author Challenge.IT
 */
public class Credentials
{
    private final String _username;
    private final String _password;

    /**
     * Cria uma instância de Credentials.
     * @param username
     * @param password
     */
    public Credentials(String username, String password)
    {
        _username = username;
        _password = password;
    }

    /**
     * @return O valor do username.
     */
    public String getUsername() { return _username; }

    /**
     * @return O valor da password.
     */
    public String getPassword() { return _password;}
}
