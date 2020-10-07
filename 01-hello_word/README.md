# Úkol číslo 1 - Hello Word

# Zadání

Máte za úkol napsat program (aplikaci), který spustíte se dvěma parametry. Těmi parametry jsou jednoduchá jména. Program je načte a vytiskne, jak je vidět v příkladu dále. 

    # java -jar HelloWord.jar Josef Novak
    > First name: Josef
    > Last name: Novak

# Spuštění

Tento program používá pro sestavení Gradle. Proto se kompiluje a spouští pomocí něj:

## Kompilace

Přejděte do složky s projektem a spusťte příkaz:

    gradle build

Pokud chcete sestavit fat-jar, tedy JAR soubor se všemi závislostmi, stačí zadat místo předchozího příkazu:

    gradle fatJar

## Spuštění

Přejděte do složky s projektem a spusťte příkaz:

    gradle run --args="FIRST_NAME LAST_NAME"

Kde nahradíte `FIRST_NAME` a `LAST_NAME` za adekvátní parametry.

Pokud jste v předchozím příkladu sestavili fat-jar, použijte příkaz:

    cd .\bin\libs
    java -jar 01-hello_word-all.jar FIRST_NAME LAST_NAME


### Poznámka

Po dlouhém pátrání se mi podařilo rozjet podporu UTF-8 jako defaultního encodingu. Stačí v konzoli zadat:

    chcp 65001

a následně spustit program viz. výše. Při sestavení bylo nutné do `build.gradle` přidat řádek:

    applicationDefaultJvmArgs = ["-Dfile.encoding=UTF-8"]

