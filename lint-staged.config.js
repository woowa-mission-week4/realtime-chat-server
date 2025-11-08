module.exports = {
  // Kotlin 파일 staged 시 ktlint 적용
  "**/*.kt": filenames => [
    `npx ktlint -F ${filenames.join(" ")}`
  ],
  "**/*.kts": filenames => [
    `npx ktlint -F ${filenames.join(" ")}`
  ]
};
