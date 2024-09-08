import os
import re
import subprocess

ignore = '*.aux|*.fdb_latexmk|*.fls|*.log|*.out|*.synctex.gz|*.toc|*.png|*.jpg|__pycache__|src/**/__pycache__|*.pyc|.git|.env'

def run():
    subprocess.run(f"tree -I '{ignore}' > temp_structure.txt", shell=True)

    with open("temp_structure.txt", "r") as structure:
        new_structure = structure.read()

    with open("README.md", "r") as readme:
        readme_content = readme.read()

    pattern = re.compile(r"## Project Structure\n```.*?```", re.DOTALL)

    if pattern.search(readme_content):
        updated_readme = pattern.sub(f"## Project Structure\n```bash\n{new_structure}```", readme_content)
    else:
        updated_readme = readme_content + f"\n## Project Structure\n```bash\n{new_structure}```"

    with open("README.md", "w") as readme:
        readme.write(updated_readme)

    os.remove("temp_structure.txt")

if __name__ == "__main__":
    run()
