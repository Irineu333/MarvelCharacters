#include <jni.h>
#include <cstring>

const char *SHA256 = "SHA-256";
const char *SIGNATURE = "95:77:F0:F8:0C:52:B2:FF:76:AD:40:FD:FE:13:ED:0C:13:F1:5E:90:8D:77:84:74:9D:AE:80:EF:90:B7:E8:57";

void verifySignature(JNIEnv *pEnv, const _jobject *contextObject) {
    jclass signatureUtilClass = pEnv->FindClass("com/neo/marvelCharacters/security/SignatureUtil");

    jfieldID signatureInstanceId = pEnv->GetStaticFieldID(
            signatureUtilClass,
            "INSTANCE",
            "Lcom/neo/marvelCharacters/security/SignatureUtil;"
    );

    jobject signatureInstance = pEnv->GetStaticObjectField(
            signatureUtilClass,
            signatureInstanceId
    );

    jmethodID getSignatureMethodId = pEnv->GetMethodID(
            signatureUtilClass,
            "getSignature",
            "(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;"
    );

    const auto javaString = (jstring) pEnv->CallObjectMethod(
            signatureInstance,
            getSignatureMethodId,
            contextObject,
            pEnv->NewStringUTF(SHA256)
    );

    const char *signature = pEnv->GetStringUTFChars(javaString, 0);

    if (strcmp(signature, SIGNATURE) != 0) {
        jclass exceptionClass = pEnv->FindClass("java/lang/IllegalArgumentException");
        pEnv->ThrowNew(exceptionClass, "Invalid signature");
    }
}