package org.venari.kc.registration;

import jakarta.ws.rs.core.MultivaluedMap;
import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.Authenticator;
import org.keycloak.models.*;
import org.keycloak.organization.OrganizationProvider;

public class RegistrationOrganizationAuthenticator implements Authenticator {

	@Override
	public void authenticate(AuthenticationFlowContext context) {
		UserModel user = context.getUser();
		if (user == null) {
			context.success();
			return;
		}

		MultivaluedMap<String, String> formData = context.getHttpRequest().getDecodedFormParameters();

		String orgName = formData.getFirst("organizationName");
		if (orgName == null || orgName.trim().isEmpty()) {
			context.success();
			return;
		}

		KeycloakSession session = context.getSession();
		OrganizationProvider orgProvider = session.getProvider(OrganizationProvider.class);

		String alias = orgName.toLowerCase().replaceAll("[^a-z0-9]+", "-").replaceAll("^-|-$", "");

		OrganizationModel org = orgProvider.getByAlias(alias);

		if (org == null) {
			org = orgProvider.create(orgName, alias);
		}

		orgProvider.addMember(org, user);

		context.success();
	}

	@Override
	public void action(AuthenticationFlowContext context) {
		UserModel user = context.getUser();
		if (user == null) {
			context.success();
			return;
		}

		MultivaluedMap<String, String> formData = context.getHttpRequest().getDecodedFormParameters();

		String orgName = formData.getFirst("organizationName");

		if (orgName == null || orgName.trim().isEmpty()) {
			context.success();
			return;
		}

		KeycloakSession session = context.getSession();
		OrganizationProvider orgProvider = session.getProvider(OrganizationProvider.class);

		String alias = orgName.toLowerCase().replaceAll("[^a-z0-9]+", "-").replaceAll("^-|-$", "");

		OrganizationModel org = orgProvider.getByAlias(alias);

		if (org == null) {
			org = orgProvider.create(orgName, alias);
		}

		orgProvider.addMember(org, user);

		context.success();
	}

	@Override
	public boolean requiresUser() {
		return true;
	}

	@Override
	public boolean configuredFor(KeycloakSession s, RealmModel r, UserModel u) {
		return true;
	}

	@Override
	public void setRequiredActions(KeycloakSession s, RealmModel r, UserModel u) {
	}

	@Override
	public void close() {
	}
}
