/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.soundcloud.api;

import org.junit.Before;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.social.soundcloud.api.impl.SoundCloudTemplate;
import org.springframework.social.test.client.MockRestServiceServer;

public abstract class AbstractSoundCloudApiTest {
	protected static final String API_KEY = "someApiKey";
	protected static final String ACCESS_TOKEN = "someAccessToken";


	protected SoundCloudTemplate soundCloud;
	protected SoundCloudTemplate unauthorizedSoundCloud;
	protected MockRestServiceServer mockServer;
	protected MockRestServiceServer mockUnauthorizedServer;

	protected HttpHeaders responseHeaders;

	@Before
	public void setup() {
		soundCloud = new SoundCloudTemplate(API_KEY,ACCESS_TOKEN);
		mockServer = MockRestServiceServer.createServer(soundCloud
				.getRestTemplate());

		responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON);

		unauthorizedSoundCloud = new SoundCloudTemplate(API_KEY);
		mockUnauthorizedServer = MockRestServiceServer
				.createServer(unauthorizedSoundCloud.getRestTemplate());
	}

	protected Resource jsonResource(String filename) {
		return new ClassPathResource(filename + ".json", getClass());
	}

}
