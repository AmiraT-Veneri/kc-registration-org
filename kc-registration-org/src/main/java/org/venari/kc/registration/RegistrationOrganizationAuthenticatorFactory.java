package org.venari.kc.registration;

import java.util.List;

import org.keycloak.Config;
import org.keycloak.authentication.Authenticator;
import org.keycloak.authentication.AuthenticatorFactory;
import org.keycloak.models.AuthenticationExecutionModel.Requirement;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.provider.ProviderConfigProperty;

public class RegistrationOrganizationAuthenticatorFactory implements AuthenticatorFactory {

    public static final String ID = "registration-org-auth";

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public String getDisplayType() {
        return "Registration Organization Assignment";
    }

    @Override
    public Authenticator create(KeycloakSession session) {
        return new RegistrationOrganizationAuthenticator();
    }

    @Override public void init(Config.Scope config) {}
    @Override public void postInit(KeycloakSessionFactory factory) {}
    @Override public void close() {}

    @Override public boolean isConfigurable() { return false; }

    @Override
    public Requirement[] getRequirementChoices() {
        return new Requirement[] {
                Requirement.REQUIRED,
                Requirement.DISABLED
        };
    }

    @Override public boolean isUserSetupAllowed() { return false; }

    @Override
    public String getHelpText() {
        return "Creates and assigns organization during registration";
    }

	@Override
	public String getReferenceCategory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProviderConfigProperty> getConfigProperties() {
		// TODO Auto-generated method stub
		return List.of();
	}
}
