# Git Commands Cheat Sheet

## Basic Git Commands You Should Know

### 1. Repository Setup
```bash
git init                    # Initialize a new repository
git clone <url>            # Clone a repository from remote
git remote -v              # View remote repositories
```

### 2. Branch Operations
```bash
git branch                 # List local branches
git branch -a              # List all branches (local + remote)
git branch <branch-name>   # Create new branch
git checkout <branch>      # Switch to branch
git checkout -b <branch>   # Create and switch to new branch
git branch -d <branch>     # Delete branch (safe)
git branch -D <branch>     # Force delete branch
```

### 3. Working with Changes
```bash
git status                 # Check repository status
git add .                  # Stage all changes
git add <file>             # Stage specific file
git commit -m "message"    # Commit staged changes
git commit -am "message"   # Stage and commit in one step
```

### 4. Remote Operations
```bash
git push origin <branch>   # Push branch to remote
git pull origin <branch>   # Pull changes from remote
git fetch                  # Fetch changes without merging
git push -u origin <branch> # Push and set upstream tracking
```

### 5. Viewing History
```bash
git log                    # View commit history
git log --oneline          # Compact commit history
git log --graph            # Visual branch history
git show <commit-hash>     # Show specific commit details
```

### 6. Merging and Rebasing
```bash
git merge <branch>         # Merge branch into current branch
git rebase <branch>        # Rebase current branch onto another
git cherry-pick <commit>   # Apply specific commit to current branch
```

### 7. Undoing Changes
```bash
git reset --soft HEAD~1    # Undo last commit, keep changes staged
git reset --hard HEAD~1    # Undo last commit, discard changes
git checkout -- <file>     # Discard changes to specific file
git stash                  # Temporarily save changes
git stash pop              # Restore stashed changes
```

### 8. Useful Shortcuts
```bash
git diff                   # Show unstaged changes
git diff --staged          # Show staged changes
git blame <file>           # Show who changed each line
git tag <tag-name>         # Create a tag
```

## Branching Strategies

### GitFlow Strategy:
- `main` - Production ready code
- `develop` - Integration branch for features
- `feature/*` - New features
- `release/*` - Release preparation
- `hotfix/*` - Critical fixes

### GitHub Flow Strategy:
- `main` - Always deployable
- `feature/*` - Short-lived feature branches
- Pull requests for code review
