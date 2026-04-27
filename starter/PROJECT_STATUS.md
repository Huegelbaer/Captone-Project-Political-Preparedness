# Political Preparedness — Project Status

This document tracks the implementation status of the Political Preparedness
capstone project against the official Udacity rubric (rubric ID 2848).

It is structured in three parts:

1. **Application features** — current state vs. target state
2. **Submission-relevant rubric criteria** — what must be done before submission
3. **Optional features** — work that is in the codebase or commonly added but
   is not required by the rubric

---

## 1. Application Features: Current vs. Target State

### Feature 1 — Launch / Onboarding screen

**Target.** Initial screen with logo and two buttons ("Upcoming Elections",
"Representatives"). Shown on first launch; on subsequent launches, the screen
may optionally be skipped via `SharedPreferences`.

**Current.** Fragment exists (`LaunchFragment.kt:9-31`), logo and both buttons
present, navigation works. Strings are hardcoded
(`fragment_launch.xml:28,37`). No first-launch skip via `SharedPreferences`.

---

### Feature 2 — Elections list (Upcoming + Saved)

**Target.** Two stacked `RecyclerView`s — top section shows upcoming elections
fetched from the Google Civics API, bottom section shows saved elections from
the local database. Tapping an item navigates to the Voter Info detail screen.

**Current.** ✅ **Fully functional.** `ElectionsFragment.kt:39-51` binds both
recycler views, `ElectionsViewModel.kt:50-82` loads API and DB data in parallel
on the IO dispatcher, `ListAdapter` with `DiffUtil`
(`ElectionListAdapter.kt:11-22`), navigation via SafeArgs
(`ElectionsFragment.kt:71-77`).
*Minor:* section headers "Upcoming"/"Saved" are hardcoded
(`fragment_election.xml:29,62`).

---

### Feature 3 — Voter Info detail screen

**Target.** Detail screen for a single election. Displays election name
(toolbar), election day, the State Election Administration Body (correspondence
address plus two clickable links: "Election Information" and "Voting
Location"). A floating action button saves/removes the election in the local
database; the heart icon reflects the saved state.

**Current.** **Partially implemented.**
- ✅ Save/Unsave FAB works end-to-end (`VoterInfoViewModel.kt:58-86`,
  custom `isFavorite` binding adapter wired in `fragment_voter_info.xml:108-112`),
  saved state is read on open via `loadSavedState()`.
- ❌ The Voter Info API is **never called** — `loadDetails(address)` in
  `VoterInfoViewModel.kt:47-54` is dead code with no caller.
- ❌ Election day, state header, "Election Information" link and "Voting
  Location" link are empty `TextView`s with no text and no click handler
  (`fragment_voter_info.xml:43-69`).
- ❌ No `loadUrl()` helper using an `Intent`.

---

### Feature 4 — Representative search

**Target.** Address input form (street, line 2, city, state spinner, ZIP). Two
buttons:

- **"Find My Representatives"** — uses the entered address to query the Civics
  API.
- **"Use My Location"** — `FusedLocationProviderClient` → `Geocoder` →
  populated address fields → Civics API.

Below the form, a `RecyclerView` shows representatives with a photo (loaded via
Glide), name, office, and clickable social icons (Facebook, Twitter, website).
A `MotionLayout` hides the form as the list scrolls.

**Current.** **Effectively missing.**
- ❌ Class is named `DetailFragment` instead of `RepresentativeFragment` —
  `RepresentativeFragment.kt:13`.
- ❌ `onCreateView` returns `null`, so navigating to the screen **crashes** —
  `RepresentativeFragment.kt:32`.
- ❌ View model is empty — `RepresentativeViewModel.kt:1-26`.
- ❌ Layout: form fields without data binding, without strings, no spinner
  adapter, **no `RecyclerView`**, `MotionLayout` without a motion scene file
  (`fragment_representative.xml:7-9,12,107`).
- ❌ Adapter only sets a static profile icon
  (`RepresentativeListAdapter.kt:35`); Glide is unused; the social-link helpers
  are defined but never invoked.
- ❌ Location: no permissions in the manifest, no `FusedLocationProviderClient`
  initialised, `getLocation()` is an empty stub.
- ✅ Building blocks already in place: the `geoCodeLocation()` helper is
  correct, the `ListAdapter` skeleton with `DiffUtil` exists, and
  `fragment_representative_list_item.xml` is set up for Glide bindings.

---

### Feature 5 — Election persistence (Room)

**Target.** Local database for saved elections, persisted across app sessions,
with all DB work performed off the UI thread.

**Current.** ✅ **Fully implemented.** `ElectionDatabase`,
`ElectionDao.kt:8-25`, `ElectionEntity` with `Converters`, repository pattern
(`ElectionRepository.kt:11-55`), DI via Koin.

---

### Feature 6 — Networking layer (Civics API)

**Target.** Retrofit + Moshi for three endpoints: `elections`, `voterinfo`,
`representatives`. API key kept outside source code.

**Current.** ✅ **Infrastructure complete.** `CivicsApiService.kt:20-29`
defines all three endpoints, Moshi is wired with custom adapters (`DateAdapter`,
`ElectionDivisionAdapter`), the API key is read from `secret.properties` via
`BuildConfig` (`build.gradle:25`, `CivicsHttpClient.kt:20`), and there is a
custom `NetworkConnectionInterceptor`.
⚠️ **However**, only `getElections()` is actually used. `getVoterInfo()` and
`getRepresentatives()` have no callers.

---

### Feature 7 — Image loading (Glide)

**Target.** Representative photos loaded asynchronously via Glide with a
loading placeholder and an error drawable.

**Current.** ❌ Glide is declared as a dependency (`build.gradle:76`) but is
**not used anywhere**. The drawables `loading_animation` and `ic_broken_image`
already exist in `res/drawable` but are not referenced.

---

### Feature 8 — Configuration changes (rotation)

**Target.** Entered address and the resulting representatives list survive
rotation via `SavedStateHandle` or `onSaveInstanceState`.

**Current.** ❌ Not implemented (the underlying feature is not yet wired up).

---

### Cross-cutting components (all ✅)

- **MVVM architecture**: view models with Koin DI, `LiveData`, observer
  pattern, `viewLifecycleOwner` for data binding.
- **Navigation Component**: `nav_graph.xml` with SafeArgs, `Election` passed
  as `Parcelable`.
- **Data Binding**: active in the Elections and Voter Info layouts with custom
  binding adapters (`ElectionBindingAdapter`, `BindingApaterUtils`).
- **Koin DI**: clean module in `MainApplication.kt:25-36`.
- **Logging**: Timber `DebugTree`.

---

## 2. Submission-Relevant Rubric Criteria

Strictly tracked against the [official Udacity rubric (ID 2848)](https://www.udacity.com/rubric/2848).

| #  | Rubric criterion                                                                  | Status        | What is missing for submission                                                                                                                                                                                                                              |
| -- | --------------------------------------------------------------------------------- | ------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 1  | ≥ 3 screens, Navigation Controller, application bundle for inter-screen data      | ✅ pending fix | Once the Representative crash is fixed (rename `DetailFragment` → `RepresentativeFragment` and make it functional), this criterion is met. Bundles are already used via SafeArgs.                                                                            |
| 2  | Flat `ConstraintLayout`, `RecyclerView` + ViewHolder, resources in `res/`, IDs    | ⚠️ partial    | Add the `RecyclerView` to `fragment_representative.xml`. Move hardcoded strings into `strings.xml` (launch buttons, election headers, content descriptions). Fill empty `TextView`s and `Button`s with text.                                                |
| 3  | `MotionLayout` with a `MotionScene` (≥ 1 `Transition` + ≥ 2 `ConstraintSet`s)     | ❌ missing    | Create `res/xml/scene_representative.xml`, reference it from the layout via `app:layoutDescription`, and use it to hide the form on scroll.                                                                                                                  |
| 4  | Retrofit + local models + Moshi + threading                                       | ⚠️ partial    | Actually call `getVoterInfo()` from `VoterInfoFragment`, and `getRepresentatives()` from the new representative view model.                                                                                                                                  |
| 5  | Glide for asynchronous image loading with placeholder + error handling            | ❌ missing    | Add a binding adapter `loadImage(view, url)` using `placeholder(loading_animation)` and `error(ic_broken_image)`; bind it in the representative item layout.                                                                                                |
| 6  | Local persistence (Room and/or `SharedPreferences`)                               | ✅ done       | Room already satisfies the rubric.                                                                                                                                                                                                                          |
| 7  | MVVM (Fragments = view, models = data, view model = logic), no leaks              | ⚠️ partial    | Implement `RepresentativeViewModel` (LiveData for address and representatives, functions for the API call and geo-address resolution).                                                                                                                       |
| 8  | Lifecycle: state save/restore, bundles, intents, permissions                      | ⚠️ partial    | Add `SavedStateHandle` to the representative view model for address + list. Add URL intent handlers in `VoterInfoFragment` for the "Election Information" and "Voting Location" buttons. Declare `ACCESS_FINE_LOCATION` + `ACCESS_COARSE_LOCATION` in the manifest and request them at runtime. |
| 9  | Hardware integration (≥ 1 component; here: location)                              | ❌ missing    | Initialise `FusedLocationProviderClient`, implement the runtime permission flow (`requestPermissions` + `onRequestPermissionsResult`), implement `getLocation()`, and wire up the `Geocoder`.                                                               |

### Blocker order (everything below is submission-critical)

1. Fix the Representative fragment crash (rename class + create a layout
   skeleton that inflates).
2. Implement `RepresentativeViewModel` and wire it to the API.
3. Add the `RecyclerView` and Glide image loading to the Representative
   feature.
4. Declare location permissions, initialise `FusedLocationProviderClient`,
   wire up `Geocoder`.
5. Create the `MotionScene` for the Representative form.
6. Add `SavedStateHandle` for rotation.
7. Voter Info: call `loadDetails()`, add URL buttons and intent handling.
8. Move strings into `strings.xml`; populate empty `TextView`s and
   `Button`s.

---

## 3. Optional Features (Not Required by the Rubric)

The following items are either already in the codebase or commonly added in the
Udacity curriculum pattern, but they are **not** required by the official
rubric.

### Already present — fine to keep as-is

- **Koin DI** — clean architecture choice; not a rubric item.
- **Repository pattern** with the `ElectionDataSource` interface.
- **Custom `NetworkConnectionInterceptor`** for connectivity errors.
- **Timber logging.**
- **Custom Moshi adapters** for `Date` and election divisions.
- **German localisation** (`values-de/strings.xml`) — bonus.
- **Coroutines + `viewModelScope`** instead of callbacks or RxJava.

### Nice-to-have candidates

- **First-launch skip via `SharedPreferences`** in `LaunchFragment` — common in
  the Udacity pattern but not part of the rubric.
- **Unit tests** (view-model tests with `InstantTaskExecutorRule` and a fake
  repository) — not part of this capstone's rubric.
- **Loading and empty states** in the list fragments — good UX but not hard
  required.
- **Pull-to-refresh** on the Elections list.
- **Material theme polish** (toolbar, colours, typography).
- **Error snackbars** instead of log-only handling on network failures.
- **`RepresentativeClickListener`** is declared but unused — could be removed
  or wired to a detail action.

### Code-quality cleanups (no rubric impact)

- Rename `RepresentativeViewModel` → `RepresentativesViewModel` for plural
  consistency.
- `MainActivity` lives under `presentation/representative/` — should sit
  closer to the application root.
- Duplicate click listener in `ElectionsFragment.kt:35,44` (same logic
  registered twice).
- `refreshLoads()` in `ElectionsViewModel` is a duplicate of `refresh()`.
- File `BindingApaterUtils.kt` is misspelled (should be
  `BindingAdapterUtils.kt`).
- `RepresentativeDiffCallback` compares `offices` instead of an official
  identifier.
