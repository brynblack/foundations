{
  inputs = {
    nixpkgs.url = "github:nixos/nixpkgs";
    flake-utils.url = "github:numtide/flake-utils";
  };

  outputs = { nixpkgs, flake-utils, ... }:
    flake-utils.lib.eachDefaultSystem (system:
      let pkgs = import nixpkgs { inherit system; };
      in {
        devShell = pkgs.mkShell {
          buildInputs = with pkgs; [ jdk23 ];
          shellHook = "export LD_LIBRARY_PATH=${
              pkgs.lib.makeLibraryPath (with pkgs; [ libGL libpulseaudio ])
            }:$LD_LIBRARY_PATH";
        };
      });
}
