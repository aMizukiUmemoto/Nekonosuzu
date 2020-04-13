package com.access_compnay.trainingproject_nekonosuzu.data

import com.access_compnay.trainingproject_nekonosuzu.data.preset.createPresetKodein
import org.kodein.di.Kodein

// データソース用のDependency Injection Container
val dataKodein: Kodein by lazy { createPresetKodein() }
