/*
 * Copyright 2010-2013 napile.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.napile.playJava4idea;

import java.util.regex.Pattern;

/**
 * @author VISTALL
 * @since 16:35/17.03.13
 */
public interface PlayJavaConstants
{
	Pattern JAR_PATTERN = Pattern.compile("play-1.\\d(.\\d)?.jar");

	String PLAY_PLAY = "play.Play";

	String PLAY_MVC_CONTROLLER = "play.mvc.Controller";

	String APPLICATION_CONF = "application.conf";

	String CONF__APPLICATION_CONF = "conf/" + APPLICATION_CONF;

	String JPDA_PORT = "jpda.port";
}
