# no_log release

Die Abhängigkeit zu SLF4J steht einer Kompilierung dieser Bibliothek
nach nativ im Wege.

SLF4J bindet per reflection ein Logging-Framework an. Bei Verwendung
eines Logging-Frameworks kann das Problem ggf. durch Konfiguration
von graalVM gelöst werden (ungeprüft).

Wenn die Logging-Funktionalität in einer Anwendung jedoch gar nicht
benötigt wird, ist eine konfiguratorische Lösung durch graalVM nicht
möglich.

Daher wird neben der Vollversion eine no_log-Version erzeugt, die
keinerlei Abhängigkeiten zu SLF4J hat und somit auf Logging-Funktionalität
verzichtet.

Vorgehen:

1. Änderungen im Code vom develop-branch in den no_log-branch mergen.
Achtung: nicht die build.gradle Datei mergen.

    Änderungen in einzelnen Dateien können z.B. so gepatcht werden
   (Ausführung in branch no_log):

        git checkout --patch develop /path/to/file

2. In der Klasse MessageProcessor in Inhalt der Method processLoggerOutput,
die Methode getLoggerLevel und alle SLF4J imports kommentieren.
3. Von dieser Version release bauen. In der Releasebezeichnung die
Version übernehmen und -no_log anhängen. Beim Erzeugen des Release
darauf achten, dass das Release vom no_log branch erzeugt wird, z.B. so:
```
gh release create v1.2.3-no_log --target no_log
```
4. Entprechend in jitpack bauen

Anwendung dieses Vorgehens z.B. im Projekt meta.