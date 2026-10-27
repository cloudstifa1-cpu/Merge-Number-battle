#!/bin/bash
# Builds Merge Grid APK. Usage: ANDROID_SDK=/path/to/sdk KEYSTORE=key.jks KSPASS=pass ./build.sh
set -e; cd "$(dirname "$0")"; BT=$ANDROID_SDK/build-tools/35.0.0; JAR=$ANDROID_SDK/platforms/android-34/android.jar
rm -rf build && mkdir -p build/classes build/assets/fonts
cp ../index.html ../manifest.json ../icon.png build/assets/; cp ../fonts/*.woff2 build/assets/fonts/
$BT/aapt2 compile --dir res -o build/res.zip
$BT/aapt2 link -o build/base.apk -I $JAR --manifest AndroidManifest.xml -A build/assets --java build/gen build/res.zip --min-sdk-version 21 --target-sdk-version 34
javac --release 8 -cp $JAR -d build/classes build/gen/com/ravens/mergegrid/R.java src/com/ravens/mergegrid/*.java
$BT/d8 --min-api 21 --lib $JAR --output build build/classes/com/ravens/mergegrid/*.class
(cd build && zip -q base.apk classes.dex)
$BT/zipalign -f 4 build/base.apk build/aligned.apk
$BT/apksigner sign --ks "$KEYSTORE" --ks-pass pass:$KSPASS --out build/MergeGrid.apk build/aligned.apk
echo built build/MergeGrid.apk
