package com.example.remoteiuserstorageprovide;

import org.keycloak.component.ComponentModel;
import org.keycloak.credential.CredentialInput;
import org.keycloak.credential.CredentialInputValidator;
import org.keycloak.credential.PasswordCredentialProvider;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.models.credential.PasswordCredentialModel;
import org.keycloak.storage.StorageId;
import org.keycloak.storage.UserStorageProvider;
import org.keycloak.storage.user.UserLookupProvider;

public class RemoteUserStorageProvider implements UserStorageProvider, UserLookupProvider, CredentialInputValidator {
    private KeycloakSession keycloakSession;
    private ComponentModel componentModel;
    private UserApiLegacyService userApiLegacyService;
    public RemoteUserStorageProvider(KeycloakSession keycloakSession, ComponentModel componentModel,UserApiLegacyService userApiLegacyService){
        this.componentModel=componentModel;
        this.keycloakSession=keycloakSession;
        this.userApiLegacyService=userApiLegacyService;
    }
    @Override
    public boolean supportsCredentialType(String credentialType) {
        return PasswordCredentialModel.TYPE.equals(credentialType);
    }

    @Override
    public boolean isConfiguredFor(RealmModel realmModel, UserModel userModel, String crendentailType) {
        return PasswordCredentialModel.TYPE.equals(crendentailType);
    }

    @Override
    public boolean isValid(RealmModel realmModel, UserModel userModel, CredentialInput credentialInput) {
        VerifyPasswordResponse verifyPasswordResponse=userApiLegacyService.verifyuserPassword(userModel.getUsername(),credentialInput.getChallengeResponse());
        if(verifyPasswordResponse==null) return false;
        return verifyPasswordResponse.getResult();
    }

    @Override
    public void close() {

    }

    @Override
    public UserModel getUserById(RealmModel realmModel, String id) {
        StorageId storageId = new StorageId(id);
        String email = storageId.getExternalId();
        return getUserByUsername(realmModel,email);
    }

    @Override
    public UserModel getUserByUsername(RealmModel realmModel, String username) {
        UserModel userModel=null;
        User user = userApiLegacyService.getUserbyUsername(username);
        if(user!=null){
            userModel=new UserAdapter(keycloakSession,realmModel,componentModel,user);
        }
        return userModel;
    }

    @Override
    public UserModel getUserByEmail(RealmModel realmModel, String s) {
        return getUserByUsername(realmModel,s);
    }
}
