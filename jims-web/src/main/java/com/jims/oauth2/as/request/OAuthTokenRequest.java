/**
 *       Copyright 2010 Newcastle University
 *
 *          http://research.ncl.ac.uk/smart/
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jims.oauth2.as.request;

import com.jims.oauth2.as.validator.ClientCredentialValidator;
import com.jims.oauth2.as.validator.PasswordValidator;
import com.jims.oauth2.as.validator.RefreshTokenValidator;
import com.jims.oauth2.common.exception.OAuthProblemException;
import com.jims.oauth2.common.exception.OAuthSystemException;
import com.jims.oauth2.common.message.types.GrantType;
import com.jims.oauth2.common.validators.OAuthValidator;
import com.jims.oauth2.as.validator.AuthorizationCodeValidator;

import javax.servlet.http.HttpServletRequest;


/**
 * The Default OAuth Authorization Server class that validates whether a given HttpServletRequest is a valid
 * OAuth Token request.
 * <p/>
 * IMPORTANT: This OAuthTokenRequest assumes that a token request requires client authentication.
 * Please see section 3.2.1 of the OAuth Specification: http://tools.ietf.org/html/rfc6749#section-3.2.1
 */
public class OAuthTokenRequest extends AbstractOAuthTokenRequest {

    /**
     * Create an OAuth Token request from a given HttpSerlvetRequest
     *
     * @param request the httpservletrequest that is validated and transformed into the OAuth Token Request
     * @throws com.jims.oauth2.common.exception.OAuthSystemException  if an unexpected exception was thrown
     * @throws com.jims.oauth2.common.exception.OAuthProblemException if the request was not a valid Token request this exception is thrown.
     */
    public OAuthTokenRequest(HttpServletRequest request) throws OAuthSystemException, OAuthProblemException {
        super(request);
    }

    @Override
    protected OAuthValidator<HttpServletRequest> initValidator() throws OAuthProblemException, OAuthSystemException {
        validators.put(GrantType.PASSWORD.toString(), PasswordValidator.class);
        validators.put(GrantType.CLIENT_CREDENTIALS.toString(), ClientCredentialValidator.class);
        validators.put(GrantType.AUTHORIZATION_CODE.toString(), AuthorizationCodeValidator.class);
        validators.put(GrantType.REFRESH_TOKEN.toString(), RefreshTokenValidator.class);
        return super.initValidator();
    }
}
