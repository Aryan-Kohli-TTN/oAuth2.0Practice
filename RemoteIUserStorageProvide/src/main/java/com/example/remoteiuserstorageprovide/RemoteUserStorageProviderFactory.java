package com.example.remoteiuserstorageprovide;

import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.storage.UserStorageProviderFactory;

public class RemoteUserStorageProviderFactory implements UserStorageProviderFactory<RemoteUserStorageProvider> {
    public static final String PROVIDER_NAME="my-storage-provider";
    @Override
    public RemoteUserStorageProvider create(KeycloakSession keycloakSession, ComponentModel componentModel) {
        return new RemoteUserStorageProvider(keycloakSession,componentModel,new UserApiLegacyService(keycloakSession));
    }

    @Override
    public String getId() {
        return PROVIDER_NAME;
    }
}
