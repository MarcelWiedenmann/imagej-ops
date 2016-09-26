package snippet;

public class Snippet {

	<plugin>
					<artifactId>maven-jar-plugin</artifactId>
					<configuration>
						<archive>
							<manifest>
								<packageName>net.imagej.ops</packageName>
							</manifest>
						</archive>
					</configuration>
				</plugin>
				<plugin>
					<groupId>org.codehaus.mojo</groupId>
					<artifactId>license-maven-plugin</artifactId>
					<configuration>
						<organizationName>Board of Regents of the University of
	Wisconsin-Madison, University of Konstanz and Brian Northan.</organizationName>
					</configuration>
				</plugin>
}

