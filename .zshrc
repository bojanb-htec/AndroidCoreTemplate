# For Unix based OS, this should be automatically called
# on entering the directory
# If it is not loaded automatically and if ~/.zshrc is loaded automatically on
# entering terminal, add these lines to your ~/.zshrc:
####################################
## if [[ -f ./.zshrc ]] && [[ ! ./.zshrc -ef ~/.zshrc ]]; then
##    source ./.zshrc
## fi
##
## function cd() {
##    unalias -a
##    builtin cd $@
##    source ~/.zshrc
##    if [[ -f ./.zshrc ]] && [[ ! ./.zshrc -ef ~/.zshrc ]]; then
##        source ./.zshrc
##    fi
##}
####################################

# Aliases for Unix based OS users
alias clean='./gradlew clean'
alias test='./gradlew test'
alias detekt='./gradlew detekt'
alias installDebug='./scripts/build-and-install-debug.sh'
