from setuptools import setup, find_packages

setup(
    name="rotacrypt",
    version="0.1",
    packages=find_packages(where="src"),
    package_dir={"": "src"},
    include_package_data=True,
    install_requires=[
        
    ],
    entry_points={
        "console_scripts": [
            "rotacrypt=src.cube.manager:main",
        ],
    },
)