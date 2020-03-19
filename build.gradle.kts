plugins {
	id("org.asciidoctor.convert") version "1.5.8"
	id("org.ajoberstar.grgit") version "4.0.1"
	id("org.ajoberstar.git-publish") version "2.1.3"
}

gitPublish {
	branch.set("gh-pages")
	contents {
		from("$buildDir/asciidoc/html5")
	}
}

tasks.gitPublishCommit {
	dependsOn(tasks.asciidoctor)
}

tasks.wrapper {
	gradleVersion = "6.2.2"
}
