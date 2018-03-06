rm -rf /Users/dev_13444/Documents/GitHub/Tamil/app/build/
rm -rf /Users/dev_13444/Documents/GitHub/Tamil/app/illakanam/
./gradlew app:clean
./gradlew app:assembleIllakanamRelease
adb uninstall com.padhuga.tamil.illakanam
adb install /Users/dev_13444/Documents/GitHub/Tamil/app/build/outputs/apk/illakanam/release/app-illakanam-release.apk

rm -rf /Users/dev_13444/Documents/GitHub/Tamil/app/build/
rm -rf /Users/dev_13444/Documents/GitHub/Tamil/app/illakiyam/
./gradlew app:clean
./gradlew app:assembleIllakiyamRelease
adb uninstall com.padhuga.tamil.illakiyam
adb install /Users/dev_13444/Documents/GitHub/Tamil/app/build/outputs/apk/illakiyam/release/app-illakiyam-release.apk

rm -rf /Users/dev_13444/Documents/GitHub/Tamil/app/build/
rm -rf /Users/dev_13444/Documents/GitHub/Tamil/app/isai/
./gradlew app:clean
./gradlew app:assembleIsaiRelease
adb uninstall com.padhuga.tamil.isai
adb install /Users/dev_13444/Documents/GitHub/Tamil/app/build/outputs/apk/isai/release/app-isai-release.apk

rm -rf /Users/dev_13444/Documents/GitHub/Tamil/app/build/
rm -rf /Users/dev_13444/Documents/GitHub/Tamil/app/nadagam/
./gradlew app:clean
./gradlew app:assembleNadagamRelease
adb uninstall com.padhuga.tamil.nadagam
adb install /Users/dev_13444/Documents/GitHub/Tamil/app/build/outputs/apk/nadagam/release/app-nadagam-release.apk

rm -rf /Users/dev_13444/Documents/GitHub/Tamil/app/build/
rm -rf /Users/dev_13444/Documents/GitHub/Tamil/app/kalaigal/
./gradlew app:clean
./gradlew app:assembleKalaigalRelease
adb uninstall com.padhuga.tamil.kalaigal
adb install /Users/dev_13444/Documents/GitHub/Tamil/app/build/outputs/apk/kalaigal/release/app-kalaigal-release.apk

rm -rf /Users/dev_13444/Documents/GitHub/Tamil/app/build/
rm -rf /Users/dev_13444/Documents/GitHub/Tamil/app/vilayaatu/
./gradlew app:clean
./gradlew app:assembleVilayaatuRelease
adb uninstall com.padhuga.tamil.vilayattu
adb install /Users/dev_13444/Documents/GitHub/Tamil/app/build/outputs/apk/vilayaatu/release/app-vilayaatu-release.apk
