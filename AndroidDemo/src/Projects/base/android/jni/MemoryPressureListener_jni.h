// Copyright (c) 2012 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

// This file is autogenerated by
//     base/android/jni_generator/jni_generator.py
// For
//     org/chromium/base/MemoryPressureListener

#ifndef org_chromium_base_MemoryPressureListener_JNI
#define org_chromium_base_MemoryPressureListener_JNI

#include <jni.h>

// Step 1: forward declarations.
namespace {
const char kMemoryPressureListenerClassPath[] =
    "org/chromium/base/MemoryPressureListener";
// Leaking this jclass as we cannot use LazyInstance from some threads.
jclass g_MemoryPressureListener_clazz = NULL;

}  // namespace

static void OnMemoryPressure(JNIEnv* env, jclass jcaller,
    jint memoryPressureType);

// Step 2: method stubs.

static base::subtle::AtomicWord g_MemoryPressureListener_registerSystemCallback
    = 0;
static void Java_MemoryPressureListener_registerSystemCallback(JNIEnv* env,
    jobject context) {
  /* Must call RegisterNativesImpl()  */
  CHECK_CLAZZ(env, g_MemoryPressureListener_clazz,
      g_MemoryPressureListener_clazz);
  jmethodID method_id =
      base::android::MethodID::LazyGet<
      base::android::MethodID::TYPE_STATIC>(
      env, g_MemoryPressureListener_clazz,
      "registerSystemCallback",

"("
"Landroid/content/Context;"
")"
"V",
      &g_MemoryPressureListener_registerSystemCallback);

     env->CallStaticVoidMethod(g_MemoryPressureListener_clazz,
          method_id, context);
  jni_generator::CheckException(env);

}

// Step 3: RegisterNatives.

static const JNINativeMethod kMethodsMemoryPressureListener[] = {
    { "nativeOnMemoryPressure",
"("
"I"
")"
"V", reinterpret_cast<void*>(OnMemoryPressure) },
};

static bool RegisterNativesImpl(JNIEnv* env) {
  g_MemoryPressureListener_clazz = reinterpret_cast<jclass>(env->NewGlobalRef(
      base::android::GetClass(env, kMemoryPressureListenerClassPath).obj()));

  const int kMethodsMemoryPressureListenerSize =
      arraysize(kMethodsMemoryPressureListener);

  if (env->RegisterNatives(g_MemoryPressureListener_clazz,
                           kMethodsMemoryPressureListener,
                           kMethodsMemoryPressureListenerSize) < 0) {
    jni_generator::HandleRegistrationError(
        env, g_MemoryPressureListener_clazz, __FILE__);
    return false;
  }

  return true;
}

#endif  // org_chromium_base_MemoryPressureListener_JNI
